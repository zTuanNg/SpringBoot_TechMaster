
const params = new URLSearchParams(window.location.search);
const URL_API = "http://localhost:8080";
const username = document.querySelector("#name");
const userEmail = document.querySelector("#email");
const userPhone = document.querySelector("#phone");
const userAvatar = document.querySelector("#avatar-preview");

const provinceEl = document.querySelector("#address");
const oldPass = document.querySelector("#old-password");
const newPass = document.querySelector("#new-password");
// const forgotPass = document.querySelector("#btn-forgot-password");

// Truy cập
const btnModalImage = document.getElementById("btn-modal-image");
const imageContainerEl = document.querySelector(".image-container");
const btnChoseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");
const btnUpdate = document.getElementById("btn-save");

const modalImageEl = document.getElementById("modal-image");

const avatarEl = document.getElementById("avatar");

let address;
let sourceImage = ""; // url image when click it 
let images = [];

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

// Detail user API
const detailUserApi = (id) =>{
    return axios.get(`${URL_API}/api/v1/users/detail/${id}`)
}

// Change password API
const changePasswordApi = (id)=>{
    return axios.put(`${URL_API}/api/v1/users/${id}/update-password`, {
        "oldPassword" : oldPass.value,
        "newPassword" : newPass.value
    })
}

// Forgot password API
const forgotPassApi = (id) =>{
    return axios.get(`${URL_API}/api/v1/users/${id}/forgot-password`)
}

// Load all images of user API
const loadImagesApi = (id) =>{
    return axios.get(`${URL_API}/api/v1/users/${id}/files`);
}

// Delete Image API
const deleteImageApi = (id, fileId) =>{
    return axios.delete(`${URL_API}/api/v1/users/${id}/files/${fileId}`);
}

// Change avatar API
const changeAvatarAPI = (id, fieldId) =>{
    return axios.put(`${URL_API}/api/v1/users/${id}/update-avatar`,{
        "avatar" : fieldId
    })
}

// Update info
const updateInfoApi = (id) =>{
    return axios.put(`${URL_API}/api/v1/users/${id}`,{
        "name" : username.value,
        "phone" : userPhone.value,
        "address" : provinceEl.value
    })

}


// -------------------------------------
const renderUsers = (user) =>{

    username.value = user.name;
    userEmail.value = user.email;
    userPhone.value = user.phone;
    if(user.avatar != null){
        userAvatar.src = "http://localhost:8080" + user.avatar;

    }

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

// --- Load images
const loadImages = async() =>{
    let id = params.get('id');
    try{
        let res = await loadImagesApi(id);
        images = res.data;
        renderImages(images);

    }catch(error){
        console.log(error);
    }
}

// Back
function goBack() {
    window.location='./index.html';
}

// Render Image
const renderImages = arr => {
    imageContainerEl.innerHTML = "";

    let html = "";
    arr.forEach(e => {
        html += `
            <div class="image-item" onclick="choseImage(this)">
                <img id="image" src="http://localhost:8080${e}" alt="" >
            </div>

        `

        // console.log(`Path image: http://localhost:8080${e} `);
    })
    imageContainerEl.innerHTML = html;
}


// Chọn ảnh
const choseImage = ele => {

    let id = params.get('id');
    sourceImage = "";

    // Xóa hết image được chọn trước đó
    const imageSelected = document.querySelector(".selected");
    if (imageSelected) {
        imageSelected.classList.remove("border-3", "border-primary", "selected");
    }

    // Highlight image vừa được click
    ele.classList.add("border-3", "border-primary", "selected");

    // Get link selected Image
    sourceImage = ele.querySelector("#image").src;

    console.log("sourceImage: " + sourceImage)

    // btnChoseImage.addEventListener("click", () =>{
    //     if(sourceImage != ""){
    //         document.querySelector("#avatar-preview").src = sourceImage;
    //         $('#modal-image').modal('hide');
    //     }
    // })

    // btnDeleteImage.addEventListener("click", async () =>{
    //     try{

    //         // split "/" to get Field id image
    //         let fieldIdArr = sourceImage.split("/");
            
    //         let fieldId = fieldIdArr[fieldIdArr.length - 1];
    //         console.log(fieldId);

    //         let isConfirm = confirm("Delete this photo ?");
    //         if(isConfirm){
    //             await deleteImageApi(id, fieldId);
    //             images = images.filter( p => p != sourceImage.split("8080")[1]);
    //             console.log(`images : ${images}`)
    //             renderImages(images);
    //         }

    //     }catch(error){
    //         console.log(error);
    //     }
    // })

   

    // Enable 2 nút "Chọn ảnh" và "Xóa ảnh"
    btnChoseImage.disabled = false;
    btnDeleteImage.disabled = false;
}


// Khi đóng modal chọn ảnh thì disabled 2 nút "Chọn ảnh" và "Xóa ảnh"
modalImageEl.addEventListener('hidden.bs.modal', () => {
    btnChoseImage.disabled = true;
    btnDeleteImage.disabled = true;
})


// UPLOAD CHOSEN IMAGE FILE
avatarEl.addEventListener("change",async(e) =>{

    let id = params.get('id');
    
    let imagefile = e.target.files[0];
    

    // Tao form data
    var formData = new FormData();
    formData.append("file", imagefile);

    try{

        let filePath = await axios.post(`${URL_API}/api/v1/users/${id}/files`, formData)
        images.unshift(filePath.data);

        console.log(images);
        renderImages(images);
        // console.log("Path uploaded: " + filePath)    
    }catch(error){
        console.log(error);
    } 
});


// Delete Image
btnDeleteImage.addEventListener("click", async () =>{
    let id = params.get('id');
    try{

        // split "/" to get Field id image
        let fieldIdArr = sourceImage.split("/");
        
        let fieldId = fieldIdArr[fieldIdArr.length - 1];
        console.log(fieldId);

        let isConfirm = confirm("Delete this photo ?");
        if(isConfirm){
            await deleteImageApi(id, fieldId);
            images = images.filter( p => p != sourceImage.split("8080")[1]);
            console.log(`images : ${images}`)
            renderImages(images);
        }

    }catch(error){
        console.log(error);
    }
})


// Set avatar
btnChoseImage.addEventListener("click", async() =>{

    let id = params.get('id');

    if(sourceImage != ""){
        try{
            let fieldIdArr = sourceImage.split("8080");
            let fieldId = fieldIdArr[1];

            await changeAvatarAPI(id, fieldId);
            document.querySelector("#avatar-preview").src = sourceImage;
            $('#modal-image').modal('hide');

        }catch(error){
            console.log(error)
        }
       
    }
    // sourceImage = "https://via.placeholder.com/200";
})


// Update info user
btnUpdate.addEventListener("click", async() =>{
    let id = params.get('id');

    try{
        let res = await updateInfoApi(id);
        console.log(res.data);
    }catch(error){
        console.log(error);
    }

})

    


   




// -----------------------

const render = async() =>{

    try{
        await detailUser();
        await getProvince();
    }catch(error){
        console.log(error)
    }

}

render();


