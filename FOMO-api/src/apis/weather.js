const axios=require('axios').create({baseURL:'http://api.weatherstack.com/'});

const getWeather=async (city)=>{
    try {
        const response=await axios.get('current',{
            params:{
                access_key:process.env.WeatherStack_API.toString(),
                query:city
            }
        })
        if(response.data.error){
            return {error:'Invalid location provided'}
        }else{
            const {temperature,weather_descriptions,feelslike}=response.data.current;
            return {data:{temperature,feelslike,description:weather_descriptions[0]}};
        }
    } catch (error) {
        return {error:'Unable to connect to API service!'}
    }
    
}

module.exports={
    getWeather
}