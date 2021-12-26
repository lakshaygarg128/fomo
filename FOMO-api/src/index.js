const express = require("express");
const { getLocation } = require("./apis/city");
const { getWeather } = require("./apis/weather");
const { foodItems } = require("./foodItems");
const { regionalFoods } = require("./regionalFoods");
const app = express();

app.use(express.json());

app.get("/weather/details", async (req, res) => {
  const latitude = req.query.latitude;
  const longitude = req.query.longitude;
  try {
    const locationResponse = await getLocation({ latitude, longitude });
    if (locationResponse.error) {
      throw new Error(locationResponse.error);
    }
    const city = locationResponse.city.toString();
    const weatherResponse = await getWeather(city);
    if (weatherResponse.error) {
      throw new Error(weatherResponse.error);
    }
    res.send({ ...weatherResponse.data, city });
  } catch (error) {
    res.status(500).send(error.message);
  }
});

app.get("/food/list", async (req, res) => {
  try {
    const weather = req.query.weather.toString();
    const allowedWeathers = ["sunny", "cold", "rainy"];
    if (allowedWeathers.includes(weather)) {
      res.send(foodItems[weather]);
    } else {
      throw new Error("Food details for given weather not available!");
    }
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
});

app.get("/food/list/regionWise", async (req, res) => {
  try {
    const latitude = parseFloat(req.query.latitude.toString());
    const longitude = parseFloat(req.query.longitude.toString());
    let region;
    if (
      latitude >= 25.286408 &&
      latitude <= 36.863541 &&
      longitude >= 73.42033 &&
      longitude <= 83.967205
    ) {
      region = "north";
    } else if (
      latitude >= 8.245918 &&
      latitude <= 19.722176 &&
      longitude >= 73.42033 &&
      longitude <= 83.967205
    ) {
      region = "south";
    } else if (
      latitude >= 19.722176 &&
      latitude <= 29.244795 &&
      longitude >= 83.967205 &&
      longitude <= 97.326742
    ) {
      region = "east";
    } else if (
      latitude >= 19.722176 &&
      latitude <= 25.286408 &&
      longitude >= 68.234784 &&
      longitude <= 73.42033
    ) {
      region = "west";
    } else {
      throw new Error("Location is out of india!");
    }
    res.send(regionalFoods[region]);
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
});

const port = process.env.PORT;
app.listen(port, () => {
  console.log(`Server running on port ${port}...`);
});
