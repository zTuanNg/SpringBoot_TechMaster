
const params = new URLSearchParams(window.location.search);
const URL_API = "http://localhost:8080";
const username = document.querySelector("#name");
const userEmail = document.querySelector("#email");
const userPhone = document.querySelector("#phone");
const provinceEl = document.querySelector("#address");
const oldPass = document.querySelector("#old-password");
const newPass = document.querySelector("#new-password");
// const forgotPass = document.querySelector("#btn-forgot-password");

let address;

// Lay danh sach Thanh pho 
const getProvince = async() =>{
    try{
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(`Value province: ${provinceEl.value}`)
        renderProvince(res.data)
    }catch(error){
        console.log(error);
    }
}

// Render province
const renderProvince = (arr) =>{

    provinceEl.innerHTML = "";
    let html = `<option>${address}</option>`; 

    arr.forEach(p => {

        html += `<option value="${p.name}">${p.name}</option>`
        
    });

    provinceEl.innerHTML = html;
}


// API 

const detailUserApi = (id) =>{
    return axios.get(`${URL_API}/api/v1/users/detail/${id}`)
}

const changePasswordApi = (id)=>{
    return axios.put(`${URL_API}/api/v1/users/${id}/update-password`, {
        "oldPassword" : oldPass.value,
        "newPassword" : newPass.value
    })
}

const forgotPassApi = (id) =>{
    return axios.get(`${URL_API}/api/v1/users/${id}/forgot-password`)
}



const renderUsers = (user) =>{

    username.value = user.name;
    userEmail.value = user.email;
    userPhone.value = user.phone;

}

// LOGIC

const detailUser = async () =>{
    let id = params.get('id');
    console.log(`id : ${id}`)

    try{
        let user = await detailUserApi(id);
        renderUsers(user.data);
        address = user.data.address;

    }catch(error){
        console.log(error);
    }
}

// ---- Change Password
const changePass = async() =>{

    let id = params.get('id');
    try{
        await changePasswordApi(id);
        // var modal = new bootstrap.Modal(document.getElementById('modal-change-password'));
        // modal.hide();
        $('#modal-change-password').modal('hide');
    }catch(error){
        console.log(error)
    }

}

// ---- Forgot Password
const forgotPass = async() =>{
    let id = params.get('id');
    try{
        await forgotPassApi(id);
        alert("New password is sent to your mail")
    }catch(error){
        console.log(error)
    }
}

// Back
function goBack() {
    window.location='./index.html';
}


const render = async() =>{

    try{
        await detailUser();
        await getProvince();
    }catch(error){
        console.log(error)
    }

}

render();


