# 🌦️ Weather Application (Spring Boot + Angular)

A **full-stack weather application** built with **Spring Boot (Backend)** and **Angular 15 (Frontend)**.  
It provides **current weather**, **5-day forecasts**, and **location search** using the [OpenWeather API](https://openweathermap.org/).

---

## 🚀 Features
- 🌍 Location search with latitude/longitude, state, and country  
- 🌦️ Current weather (temperature, humidity, wind speed, min/max, feels_like)  
- 📅 5-day forecast with conditions (rain, clouds, clear sky, etc.)  
- ❤️ User-friendly Angular UI with search, forecast cards, and map view  
- ✅ Health check endpoint for backend monitoring  

---

## 🛠️ Tech Stack
- **Backend**: Spring Boot 3, Java 17, Maven, Lombok, RestTemplate  
- **Frontend**: Angular 15, TypeScript, Bootstrap, FontAwesome, Leaflet (map support)  
- **API Provider**: OpenWeather API  

---

## ⚙️ Setup & Installation

### 🔹 1. Clone the Repository
```
git clone https://github.com/1ABISHEK/Weather_API_Service.git
cd Weather_API_Service
```

**##🔹 2. Backend Setup (Spring Boot)**


**1. Open application.properties and configure:**

weather.provider.openweather.url=https://api.openweathermap.org
weather.provider.openweather.apikey=YOUR_API_KEY
server.port=8080

**2. Build and Run backend:**
 ```
mvn clean install
mvn spring-boot:run
```

**3. Backend will run at:**
👉 http://localhost:8080

**🔹 3. Frontend Setup (Angular 15)**

**1. Navigate to frontend folder (if separate):**
```
cd frontend
```

**2. Install dependencies:**
```
npm install
```
**3. Start Angular app:**
```
ng serve --open
```

**4. Frontend runs at:**
👉 http://localhost:4200



## 📡 API Endpoints

### 🟢 Health Check  
```pgsql
GET /health 
```
**Response:**  
```json
{ 
  "status": "UP", 
  "timestamp": 1698765432123 
}
```

**🟢 Location Search**
```pgsql
GET /locations/search?q={city}
```
**Response:**
```json
[
  {
    "name": "Salem",
    "state": "Tamil Nadu",
    "country": "IN",
    "latitude": 11.6643,
    "longitude": 78.1460
  }
]
```
**🟢 Current Weather **
```pgsql
GET /weather/current?location={city}
```
**Response:**
```json
{
  "location": "Chennai",
  "condition": "clear sky",
  "temperature": 31.2,
  "feels_like": 33.5,
  "minTemp": 29.0,
  "maxTemp": 34.0,
  "humidity": 70,
  "windSpeed": 5.1
}
```
**🟢 Forecast**
```pgsql
GET /weather/forecast?location={city}&days={n}
```
**Response:**
```json
{
  "location": "Chennai",
  "days": 5,
  "forecasts": [
    {
      "date": "2025-09-29",
      "condition": "light rain",
      "minTemp": 25.1,
      "maxTemp": 32.5
    }
  ]
}
```

**Project Structure**
**Backend**
```bash
src/main/java/com/example/weatherapi
│── controller/      # REST Controllers
│── service/         # Business Logic
│── client/          # OpenWeather API client
│── model/dto/       # DTOs for external API responses
│── model/response/  # Responses returned to frontend
```

**Frontend**
```bash
src/app
│── services/              # Weather + Location + Health services
│── current-weather/       # Component for current weather + forecast
│── search-location/       # Component for location search
│── forecast-weather/      # Forecast graph/cards
│── app.module.ts
```

## 🖥️ Frontend UI Highlights

- 🔍 **Search Bar with Autocomplete** (location search)  
- 🌡️ **Current Weather Panel**:  
  - Temperature  
  - Condition  
  - Humidity  
  - Wind Speed  
  - Min/Max Temp  
  - Feels Like  
- 📅 **Forecast Cards** (5–7 days)  
- 🌍 **World Map Integration** (Leaflet.js) with animated marker for searched city  
- 📱 **Responsive Design** with Bootstrap & CSS grid  


## 🧪 Testing

- 📬 Use **Postman** for backend API testing  
- 🌐 Use **Angular UI** for end-to-end testing
  
### 🎬 Project Demo

[[Click to watch demo video](videos/.mp4)](https://github.com/user-attachments/assets/fd223032-71ed-4123-99ad-62333d1ea7cd)


## Output:

https://github.com/user-attachments/assets/fb7580a0-1b6f-4310-a52a-fc3444360fa0

<img width="1920" height="1080" alt="Login" src="https://github.com/user-attachments/assets/64912b01-726e-4e3a-bfc6-b94bc79d2bdc" />

<img width="1920" height="1080" alt="Dashboard" src="https://github.com/user-attachments/assets/66da7494-66ff-43cb-a7f6-cc286b8c6ebf" />

<img width="1920" height="1080" alt="Location_Madurai" src="https://github.com/user-attachments/assets/4ca3b71e-0f9d-47d7-9ff6-fb6f3c100817" />

<img width="1920" height="1080" alt="Sakha Republic" src="https://github.com/user-attachments/assets/dd9a0d01-5142-40f0-b94c-f845841568a4" />

<img width="1920" height="1080" alt="Ladakh" src="https://github.com/user-attachments/assets/316285d4-c442-46f1-b29a-7fcf820f890a" />

<img width="1024" height="610" alt="Map" src="https://github.com/user-attachments/assets/492786a5-99c6-41a6-83d7-6f5fc4fd4e76" />
