const axios=require('axios').create({baseURL:'https://api.openweathermap.org/geo/1.0/'})

const getLocation=async ({latitude,longitude})=>{
    try {
        const response=await axios.get('reverse',{
            params:{
                lat:latitude,
                lon:longitude,
                limit:1,
                appid:process.env.OpenWeather_API.toString()
            }
        })
        if(response.data.message){
            return {error:response.data.message}
        }else{
            const city=response.data[0].name
            return {city};
        }
    } catch (error) {
        return {error:'Unable to connect to API service!'}
    }
}

module.exports={
    getLocation
}