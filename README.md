# NttData_TEST


Prueba de Spring Boot y WebFlux con Postgress and Docker


Servicios desarrollados 

API 1:
-------------
localhost:8080/api/conversion/create


{
   "data":
    {
        "model":"TUCSON",
        "cryptocurrency":"ETH"
    }
}

API 2:
---------
localhost:8080/api/purchase/save

{
   "data":
    {
        "model":"TUCSON",
        "version": "ALL NEW TUCSON TA HGS",
        "cryptocurrency":"ETH",
        "convertionId":"409-134-875-1150",
        "fullName": "Juan Perez"
    }
}

API 3:
---------------
localhost:8080/api/report/generate

{
   "data":
    {
       "date": "2023-07-30",
        "model": "TUCSON",
        "cryptocurrency": "ETH"
    }
}
