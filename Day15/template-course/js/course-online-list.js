

const URL_API = "http://localhost:8080";


const courseList = document.querySelector(".course-list");
const topicItems = document.querySelectorAll(".topic-item input");
const searchKeyword = document.querySelector(".search-form-input");


let topic = "";
let keyword= "";

// API

const getCourseAPI = (topic, keyword) =>{
    return axios.get(`${URL_API}/api/v1/courses/online?topic=${topic}&keyword=${keyword}`);
}


// LOGIC

const filterCourse = async () =>{
    try{
        let res = await getCourseAPI(topic, keyword); // Api get all course

        console.log(res.data);

        console.log(`Current URL : ${document.URL}`)

        console.log(`keyword: ${typeof searchKeyword.value}`);


        // Render Course
        renderCourse(res.data);

    }catch(error){
        console.log(error)
    }
}


const renderCourse = (arr) =>{

    courseList.innerHTML = "";
    
    let html = "";

    if(arr.length == 0){
        courseList.innerHTML = "Không tìm được khoá học phù hợp...";
        return;
    }

    arr.forEach(course => {
        html += `
            <div class="col-md-4">
                <a href="./detail.html?id=${course.id}">
                    <div class="course-item shadow-sm rounded mb-4">
                        <div class="course-item-image">
                            <img src="${course.image}"
                            alt="Khoa hoc">
                        </div>
                        <div class="course-item-info p-3">
                            <h2 class="fs-5 mb-3 text-dark">${course.title}</h2>
                            <p class="type fw-light text-black-50 ">${course.type}</p>
                        </div>
                    </div>
                </a>
            </div>
        `
    });

    courseList.innerHTML = html;
    
}



const getSelectedOption = () =>{
    for(let i = 0 ; i < topicItems.length; i++){
        if(topicItems[i].checked){
            return topicItems[i].value;
        }
    }
}



topicItems.forEach(btn =>{
    btn.addEventListener("change", () =>{
        topic = getSelectedOption();
        console.log(`topic after click: ${topic}`)
        filterCourse();

    })
})


const searchWithKeyword = () =>{
    keyword = searchKeyword.value;
    filterCourse();
}



filterCourse();

