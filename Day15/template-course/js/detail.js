

const params=new URLSearchParams(window.location.search);
const URL_API = "http://localhost:8080";

// API

const getCourse = () =>{

    let id = params.get('id');
    return axios.get(`${URL_API}/api/v1/course-detail/${id}`)
}


const getCourseDetail = async() =>{

    try{
        let courseDetail = await getCourse();
        console.log(courseDetail.data)
    }catch(error){
        console.log(error);
    }
}



const renderCourseDetail(obj){
    
}


// RUN
getCourseDetail();
