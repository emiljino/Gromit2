// The city id for Bristol. Using id other name prevents conflict with other cities named Bristol
const city = "2654675"

const getWeatherData = async (api, set) => {
        await fetch(`https://api.openweathermap.org/data/2.5/weather?id=${city}&appid=${api}&units=metric`)
        .then(res => res.json())
        .then(data => set(data))
}

const getIconUrl = (data) => {
    return `https://openweathermap.org/img/wn/${icon(data)}@2x.png`
}

const temperature = (data) => { //gets the current temperature data
    return data.main.temp.toFixed(1)
}

const icon = (data) => { //gets the current weather icon, e.g. if its Sunny, a picture of the sun
    if (typeof data.weather != 'undefined') {
        return data.weather[0].icon
    }
}
 
export {getWeatherData, temperature, getIconUrl}
