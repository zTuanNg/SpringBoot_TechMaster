const usernameEle = document.getElementById('username');
const passwordEle = document.getElementById('password');


const btnLogin = document.getElementById('btn-register');
const inputEles = document.querySelectorAll('.input-row');
const loginForm = document.querySelector('.register-form')

const showInfo = document.querySelector('.show-info');



btnLogin.addEventListener("click", async() => {
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
    let isValid = checkValid();

    if(isValid){

        try{

            let res = await axios.post("http://localhost:8080/login",{
                "username" : usernameEle.value,
                "password" : passwordEle.value
            });
            console.log(res);

            showInfoUser(res.data);

        }catch(error){
            console.log(error);
            alert("Username or password incorrect")
        }

    }   
    
})


function checkValid(){

    let check = true;
    let usernameValue = usernameEle.value;
    let passwordValue = passwordEle.value;

    if(usernameValue == ''){
        setError(usernameEle, 'Enter username');
        check = false;
    }else{
        setSuccess(usernameEle);
    }
    if(passwordValue == ''){
        setError(passwordEle, 'Enter password');
        check = false;
    }else{
        setSuccess(passwordEle);
    }

    return check;
}

function setSuccess(ele) {
    ele.parentNode.classList.add('success');
}

function setError(ele, message) {
    let parentEle = ele.parentNode;


    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}

function showInfoUser(user){
    let html = "";
    let title = `<h1>Hello ${user.username}</h1>`;
    let email = `<p>Email: ${user.email}</p>`;
    let avatar = `<img src="${user.avatar}" alt="${user.userName}" width="150">`
    html = title + email + avatar ;

    showInfo.innerHTML = html;
    showInfo.classList.remove('hidden');
    loginForm.classList.add('hidden')
}