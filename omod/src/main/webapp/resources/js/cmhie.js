function openmrsLogin() {
    username= $("#userField").val();
    password= $("#passField").val();
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + password));
        },
        url: "/openmrs/ws/rest/v1/session",
        success: function(result){
            window.location = "/openmrs/referenceapplication/home.page?noredirect=true";
        }
    });
}


function cmhieLogin() {
    username= $("#patientUserName").val();
    password= $("#passcode").val();
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + passcode));
        },
        url: "/openmrs/ws/rest/v1/session",
        success: function(result){
            window.location = "/openmrs/referenceapplication/home.page?noredirect=true";
        }
    });
}


function verifyUsername() {
    username= $("#patientUserName").val();
    if(username.length!=0) {
        $.("#passCodeDiv").css("visibility","visible");
    }
}

function submit() {


}