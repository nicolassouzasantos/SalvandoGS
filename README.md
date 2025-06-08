Links:
Repositório GitHub: https://github.com/nicolassouzasantos/SalvandoGS
Link do deploy no Render: https://salvandogs.onrender.com ou já na página da documentação do Swagger: https://salvandogs.onrender.com/swagger-ui/index.html (swagger dá erro 500 ao abrir depois de fazer o deploy, tentei de tudo pra ajustar mas não achei a resposta, abaixo segue as tabelas da documentação).
Link para vídeo demonstração da solução completa: [https://youtu.be/-mk3kKzbZC0?si=PGk_APBmZzJLvnsV](https://youtu.be/OfXLEy6iv80?si=p3Hi1vh0lEpX5VvM)

Vamos aos testes do CRUD: 

Classe Usuario:
GET https://dashboard.render.com/api/usuarios
POST https://dashboard.render.com/api/usuarios 
body JSON: { "nome": "Teste da Silva", "email": "teste.silva@email.com", "senha": "123456789", "telefone": "(11) 11111-1111", "endereco": "Rua dos Testes, 123 - São Paulo, SP" }
PUT https://dashboard.render.com/api/usuarios/{id} 
body JSON: { "nome": "Teste da Silva", "email": "teste.silva@email.com", "senha": "1234567", "telefone": "(11) 22222-2222", "endereco": "Rua dos Testes, 123 - São Paulo, SP" }
DELETE https://dashboard.render.com/api/usuarios/{id}

Classe Sensor:
GET https://dashboard.render.com/api/sensores
POST https://dashboard.render.com/api/sensores body JSON: { "numeroSensor": 1004, "latitude": -55, "longitude": -50 }
PUT https://dashboard.render.com/api/sensores/{id} body JSON: { "numeroSensor": 1004, "latitude": -60, "longitude": -60 }
DELETE https://dashboard.render.com/api/sensores/{id}

Classe Evento:
GET https://dashboard.render.com/api/eventos
POST https://dashboard.render.com/api/eventos body JSON: { "dataHora": "2024-06-08T14:30:00", "idSensor": 1004 }
DELETE https://dashboard.render.com/api/eventos/{id}
