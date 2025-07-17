async function signIn(){
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    
    const signIn = {
        email:email,
        password:password
    };
    
    const signInJson = JSON.stringify(signIn);
    
    const response = await fetch(
        "SignIn",
        {
            method:"POST",
            body:signInJson,
            header:{
                "Content-Type":"application/json"
            }
        }
    );
    
    if (response.ok) { //success
        const json = await response.json();
        
        if (json.status) { //true
            if (json.message==="1") {
                window.location="verify-account.html";
            }else{
                window.location="index.html";
            }
        }else{
            document.getElementById("message").innerHTML =json.message;
        }
    }else{
        document.getElementById("message").innerHTML = "SignIn failed. Please try again";
    }
}

//async function authenticateUser(){
//    const response = await fetch("SignIn");
//    
//    if (response.ok) {
//        const json = await response.json();
//        if (json.message === "1") {
//            window.location = "index.html";
//        }
//    }else{
//        
//    }
//}


