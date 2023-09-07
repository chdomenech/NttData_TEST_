package com.nttdata.costconversion.infrastructure.adapter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.costconversion.application.input.CarInfoVO;
import com.nttdata.costconversion.application.input.CryptoInfoVO;
import com.nttdata.costconversion.application.input.ModelsVO;
import com.nttdata.costconversion.application.input.conversion.InputConversionVO;
import com.nttdata.costconversion.application.output.ConvertionOutputVO;
import com.nttdata.costconversion.domain.model.ConversionEntity;
import com.nttdata.costconversion.domain.model.VersionsEntity;
import com.nttdata.costconversion.domain.repository.ConversionRepository;
import com.nttdata.costconversion.domain.repository.VersionRepository;
import com.nttdata.costconversion.domain.service.ConversionService;
import com.nttdata.costconversion.infrastructure.adapter.util.CoreUtils;

import reactor.core.publisher.Flux;

@Service
public class ConversionServiceImplementation implements ConversionService {

	
	public static final Integer TIME_LIFE = 20;
	public static final String ERROR_MODEL_NOT_FOUND = "Modelo de auto no encontrado";
	public static final String ERROR_CRYPTO_NOT_FOUND = "Criptomoneda no encontrada";
	
	private final static String URL_WS_ACCENT = "https://kerner.hyundai.com.ec/api/versiones/1/1036";
	private final static String URL_WS_TUCSON = "https://kerner.hyundai.com.ec/api/versiones/1/1031";
	private final static String URL_WS_SANTE_FE = "https://kerner.hyundai.com.ec/api/versiones/1/1023";
	private final static String URL_WS_GRAND_I10 = "https://kerner.hyundai.com.ec/api/versiones/1/1038";

	private final static String URL_BTC = "https://api.coinlore.net/api/ticker/?id=90";
	private final static String URL_ETH = "https://api.coinlore.net/api/ticker/?id=80";

	@Autowired
	private ConversionRepository convertionRepository;

	@Autowired
	private VersionRepository versionRepository;

	@Override
	public ConvertionOutputVO createConversion(InputConversionVO data) throws Exception {
		ConvertionOutputVO convertion = null;
		List<ModelsVO> models = new ArrayList<>();
		List<VersionsEntity> versions = new ArrayList<>();
		final ConversionEntity conversionEntitySaved;
		

		final String model = !data.getData().getModel().isEmpty() ? data.getData().getModel().toUpperCase()
				: data.getData().getModel();
		
		final String cryptoCurrency = !data.getData().getCryptocurrency().isEmpty()
				? data.getData().getCryptocurrency().toUpperCase()
				: data.getData().getCryptocurrency();
		
		
		List<CarInfoVO> carsModels = getModels(model);
		List<CryptoInfoVO> cryptoInfo = getCrypto(cryptoCurrency);

			carsModels.stream().forEach(t -> {
				ModelsVO modelsVO = new ModelsVO();
				modelsVO.setVersion(t.getName());
				modelsVO.setPriceCryptocurrency(t.getFinalPrice() / cryptoInfo.get(0).getPrice());
				modelsVO.setCryptocurrency(cryptoCurrency);
				modelsVO.setModel(model);
				modelsVO.setPriceUsd(t.getFinalPrice());
				models.add(modelsVO);
			});

		ConversionEntity conversionEntity = new ConversionEntity();
		conversionEntity.setConversionId(CoreUtils.generateId());
		conversionEntity.setCryptoCurrency(cryptoCurrency);
		conversionEntity.setModel(model);
		conversionEntity.setTimeLife(TIME_LIFE);
		conversionEntitySaved = convertionRepository.save(conversionEntity);

		models.stream().forEach(t -> {
			VersionsEntity version = new VersionsEntity();
			version.setConversion(conversionEntitySaved);
			version.setVersion(t.getVersion());
			version.setPriceCryptocurrency(t.getPriceCryptocurrency());
			version.setPriceUsd(t.getPriceUsd());
			versions.add(version);
		});

		versionRepository.saveAll(versions);

		convertion = new ConvertionOutputVO();
		convertion.setConversionTimelife(conversionEntity.getTimeLife());
		convertion.setConvertionId(conversionEntity.getConversionId());
		convertion.setVersions(models);
		
		return convertion;
	}
	
	
	/**
	 * Get Models
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private List<CarInfoVO> getModels(String model) throws Exception{		
		String urlServiceAuto = selectServiceModels(model);
		Flux<CarInfoVO> cars = WebClient.create(urlServiceAuto).get().retrieve().bodyToFlux(CarInfoVO.class);
		List<CarInfoVO> carsModels = cars.collect(Collectors.toList()).share().block();
		return carsModels;
	}
	
	/**
	 * Get Crypto price
	 * @param crypto
	 * @return
	 * @throws Exception
	 */
	private List<CryptoInfoVO> getCrypto(String crypto) throws Exception{
		
		String urlServiceCrypto = selectCryptoCurrency(crypto);
		Flux<CryptoInfoVO> cryptosInfo = WebClient.create(urlServiceCrypto).get().retrieve().bodyToFlux(CryptoInfoVO.class);
		List<CryptoInfoVO> cryptos = cryptosInfo.collect(Collectors.toList()).share().block();
		return cryptos;
	}

	/**
	 * Funcion que sirve para seleccionar el servicio de modelos
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	static String selectServiceModels(String model) throws Exception {
		return switch (model) {
		case "ACCENT" -> URL_WS_ACCENT;
		case "TUCSON" -> URL_WS_TUCSON;
		case "SANTA FE" -> URL_WS_SANTE_FE;
		case "GRAND I10" -> URL_WS_GRAND_I10;
		default -> throw new Exception(ERROR_MODEL_NOT_FOUND);
		};
	}

	/**
	 * Funcion que sirve para seleccionar el servicio de la criptomoneda
	 * 
	 * @param crypto
	 * @return
	 * @throws Exception
	 */
	static String selectCryptoCurrency(String crypto) throws Exception {
		return switch (crypto) {
		case "BTC" -> URL_BTC;
		case "ETH" -> URL_ETH;
		default -> throw new Exception(ERROR_CRYPTO_NOT_FOUND);
		};
	}


}
