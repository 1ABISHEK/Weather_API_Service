# 🌦️ Weather Application (Spring Boot + Angular)

A **full-stack weather application** built with **Spring Boot (Backend)** and **Angular 15 (Frontend)**.  
It provides **current weather**, **7-day forecasts**, and **location search** using the [OpenWeather API](https://openweathermap.org/).

---

## 🚀 Features
- 🌍 Location search with latitude/longitude, state, and country  
- 🌦️ Current weather (temperature, humidity, wind speed, min/max, feels_like)  
- 📅 7-day forecast with conditions (rain, clouds, clear sky, etc.)  
- ❤️ User-friendly Angular UI with search, forecast cards, and map view  
- ✅ Health check endpoint for backend monitoring  

---

## 🛠️ Tech Stack
- **Backend**: Spring Boot 3, Java 17, Maven, Lombok, RestTemplate  
- **Frontend**: Angular 15, TypeScript, Bootstrap, FontAwesome, Chart.js (optional), Leaflet (map support)  
- **API Provider**: OpenWeather API  

---

## ⚙️ Setup & Installation

### 🔹 1. Clone the Repository
```
git clone https://github.com/1ABISHEK/Weather_API_Service.git
cd Weather_API_Service

```

##🔹 2. Backend Setup (Spring Boot)


1. Open application.properties and configure:

weather.provider.openweather.url=https://api.openweathermap.org
weather.provider.openweather.apikey=YOUR_API_KEY
server.port=8080

2. Build and Run backend:
 ```
mvn clean install
mvn spring-boot:run

```

3. Backend will run at:
👉 http://localhost:8080

🔹 3. Frontend Setup (Angular 15)

Navigate to frontend folder (if separate):
```
cd frontend

```

Install dependencies:
```
npm install

```
Start Angular app:
```
ng serve --open
```

Frontend runs at:
👉 http://localhost:4200

API Endpoints
🟢 Health Check
```
GET /health

```








