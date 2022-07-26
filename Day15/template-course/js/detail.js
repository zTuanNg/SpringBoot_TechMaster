

const params=new URLSearchParams(window.location.search);
const URL_API = "https://mini-courses-website.herokuapp.com";
const container = document.querySelector(".container");
// API

const getCourse = () =>{

    let id = params.get('id');
    return axios.get(`${URL_API}/api/v1/course-detail/${id}`)
}


const getCourseDetail = async() =>{

    try{
        let courseDetail = await getCourse();
        renderCourseDetail(courseDetail.data);
    }catch(error){
        console.log(error);
        let html = "";
        html = `
        <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="mb-4">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="./course-list.html">Khóa học</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Không tìm thấy thông tin khoá học phù hợp</li>
                    </ol>
                </nav>
            </div>
            </div>
            </div>
        `
        container.innerHTML = html;
        
    }
}



const renderCourseDetail = (obj) =>{

    let html = "";
       
    html = `
    <div class="row justify-content-center">
    <div class="col-md-10">
        <div class="mb-4">
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="./course-list.html">Khóa học</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${obj.course.title}</li>
                </ol>
            </nav>
        </div>

        <div class="main p-4 shadow-sm">
            <h2 class="course-title fs-5">${obj.course.title}</h2>

            <hr>

            <div class="supporter d-flex align-items-center">
                <div class="supporter-image">
                    <img src="${obj.supporter.avatar}" alt="tư vấn viên" class="rounded-circle w-75 h-75">
                </div>
                <div class="supporter-info">
                    <p>
                        <b>Tư vấn viên :</b>
                        ${obj.supporter.name};
                    </p>
                    <p>
                        <b>Email :</b>
                        ${obj.supporter.email};
                    </p>
                    <p>
                        <b>Số điện thoại :</b>
                        ${obj.supporter.phone};
                    </p>
                </div>
            </div>

            <hr>

            <div class="course-description">
                <p>${obj.course.description}</p>
            </div>
        </div>
    </div>
</div>
    `
    container.innerHTML = html;
}


// RUN
getCourseDetail();
