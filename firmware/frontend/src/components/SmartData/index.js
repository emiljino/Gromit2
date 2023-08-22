import React, { useState, useEffect } from 'react'
import styles from './SmartData.css'
import {getWeatherData, temperature, getIconUrl} from '../../lib/SmartData'


const SmartData = ({api = "603f992184d495775ca33f82da672e62" }) => {
    const [weather, setWeather] = useState(null)

    useEffect(() => {
        getWeatherData(api, setWeather)
        const interval = setInterval(() => {
            getWeatherData(api, setWeather)
            console.log("updating weather")
        }, 20000)
        return () => clearInterval(interval) 
    }, [api])

    if (api == null) {
        console.warn("No api key provided, smart data will not be available")
        return (null)
    }

    if (weather == null || weather.main == null) {
        console.warn("Unable to access weather data")
        return (null)
    } else {
        return <div className="smartData">
            <div className="absolute bottom-0 right-50% bg-gradient-to-tr from-blue-500 to-blue-300 rounded-lg">
            <div className="p-3 flex flex-row inline-flex items-center">
            <div>
                <img id="weather" src={getIconUrl(weather)} alt='Weather Icon'/>
            </div>
            <div>
                <p id="temperature">{temperature(weather)}°C</p>
            </div>
            </div>
            </div>
            </div>
    }
}

export default SmartData
