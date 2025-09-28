🌦️ Weather Application (Spring Boot + Angular)

A full-stack weather application built with Spring Boot (Backend) and Angular 15 (Frontend).
It provides current weather, 7-day forecasts, and location search using the OpenWeather API
.

🚀 Features

🌍 Location search with latitude/longitude, state, and country

🌦️ Current weather (temperature, humidity, wind speed, min/max, feels_like)

📅 7-day forecast with conditions (rain, clouds, clear sky, etc.)

❤️ User-friendly Angular UI with search, forecast cards, and map view

✅ Health check endpoint for backend monitoring

🛠️ Tech Stack

Backend: Spring Boot 3, Java 17, Maven, Lombok, RestTemplate

Frontend: Angular 15, TypeScript, Bootstrap, FontAwesome, Chart.js (optional), Leaflet (map support)

API Provider: OpenWeather API

⚙️ Setup & Installation
🔹 1. Clone the Repository
git clone https://github.com/1ABISHEK/Weather_API_Service.git
cd Weather_API_Service

🔹 2. Backend Setup (Spring Boot)

Open application.properties and configure:

weather.provider.openweather.url=https://api.openweathermap.org
weather.provider.openweather.apikey=YOUR_API_KEY
server.port=8080


👉 Get a free API key: https://openweathermap.org/api

Build and Run backend:

mvn clean install
mvn spring-boot:run


Backend will run at:
👉 http://localhost:8080

🔹 3. Frontend Setup (Angular 15)

Navigate to frontend folder (if separate):

cd frontend


Install dependencies:

npm install


Start Angular app:

ng serve --open


Frontend runs at:
👉 http://localhost:4200

📡 API Endpoints
🟢 Health Check
GET /health


Response:

{ "status": "UP", "timestamp": 1698765432123 }

🟢 Location Search
GET /locations/search?q={city}


Response:

[
  {
    "name": "Salem",
    "state": "Tamil Nadu",
    "country": "IN",
    "latitude": 11.6643,
    "longitude": 78.1460
  }
]

🟢 Current Weather
GET /weather/current?location={city}


Response:

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

🟢 Forecast
GET /weather/forecast?location={city}&days={n}


Response:

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

🖥️ Frontend UI Highlights

Search Bar with Autocomplete (location search)

Current Weather Panel:

Temperature

Condition

Humidity

Wind Speed

Min/Max Temp

Feels Like

Forecast Cards (5–7 days)

World Map Integration (Leaflet.js) with animated marker for searched city

Responsive Design with Bootstrap & CSS grid

📂 Project Structure
Backend
src/main/java/com/example/weatherapi
│── controller/      # REST Controllers
│── service/         # Business Logic
│── client/          # OpenWeather API client
│── model/dto/       # DTOs for external API responses
│── model/response/  # Responses returned to frontend

Frontend
src/app
│── services/              # Weather + Location + Health services
│── current-weather/       # Component for current weather + forecast
│── search-location/       # Component for location search
│── forecast-weather/      # Forecast graph/cards
│── app.module.ts

🧪 Testing

Use Postman for backend API testing

Use Angular UI for end-to-end testing

🤝 Contributing

Fork the repo

Create a branch (feature/your-feature)

Commit and push

Open a Pull Request

📜 License

This project is licensed under the MIT License.
