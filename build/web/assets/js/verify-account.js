async function verifyAccount() {
    const verificationCode = document.getElementById("verifyCode").value;

    const vcode = {
        vCode: verificationCode
    };

    const verifyJson = JSON.stringify(vcode);

    const response = await fetch(
            "verifyAccount",
            {
                method: "POST",
                body: verifyJson,
                header: {
                    "Content-Type": "application/json"
                }
            }
    );

    if (response.ok) {
        const json = await response.json();

        if (json.status) { //true
            window.location = "index.html";
        } else {

            if (json.message === "1") {
                window.location = "sign-in.html";
            } else {
                document.getElementById("message").innerHTML = json.message;

            }

        }
    } else {
        document.getElementById("message").innerHTML = "Verification failed. Please try again";
    }
}


