 // toastr config
toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}



/// ---------------

const URL_API = "http://localhost:8080";

const nameUser = document.querySelector("#name");
const emailUser = document.querySelector("#email");
const phoneUser = document.querySelector("#phone");
const passUser = document.querySelector("#password");

const provinceEl = document.querySelector("#address");



// Lay danh sach Thanh pho 
const getProvince = async() =>{
    try{
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvince(res.data)
    }catch(error){
        console.log(error);
    }
}

// Render province
const renderProvince = (arr) =>{

    provinceEl.innerHTML = "";
    let html = "<option hidden>--Chon--</option>" 

    arr.forEach(p => {

        html += `<option value="${p.name}">${p.name}</option>`
        
    });

    provinceEl.innerHTML = html;
}

// API 

// private String name;
//     private String email;
//     private String phone;
//     private String address;
//     private String avatar;
//     private String password;

const createApi = () =>{
    return axios.post(`${URL_API}/api/v1/users`,{

        "email" : emailUser.value,
        "name"  : nameUser.value,
        "phone" : phoneUser.value,
        "avatar" : null,
        "password" : passUser.value,
        "address" : provinceEl.value

    });
}

// LOGIC

const createNewUser = async() =>{
    try{
        let newUser = await createApi();

        toastr.success("Create user successful");

        setTimeout(() => { window.location='./index.html';}, 800);

       

        console.log(`New User : ${newUser}`)
    }catch(error){
        console.log(error);
    }
}

//RUN

getProvince();





