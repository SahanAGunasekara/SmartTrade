async function signUp(){
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    
    const user={
        firstName:firstName,
        lastName:lastName,
        email:email,
        password:password
    };
    
    const userJson = JSON.stringify(user);
    
    const response = await fetch(
        "SignUp",
        {
            method:"POST",
            body:userJson,
            header:{
                "Content-Type":"application/json"
            }
        }
    );
     
    if (response.ok) { //success
        const responseJSON = await response.json();
        if (responseJSON.status) { //true
            
            window.location = "verify-account.html";
        }else{ // when status false
            document.getElementById("message").innerHTML=responseJSON.message;
           // console.log(responseJSON.message);
        }
        
    }else{
        document.getElementById("message").innerHTML="Registration failed. Please try again";
        
    }
    
}


