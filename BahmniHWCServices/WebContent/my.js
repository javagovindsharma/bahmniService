
function lo(){
   
    $.ajax({
        url: "https://13.232.102.174/openmrs/ws/rest/v1/person?q=govind&v=full&limit=20&startIndex=0",
        type: "GET", //This is what you should chage
        dataType: "application/json; charset=utf-8",
        username: "superman", // Most SAP web services require credentials
        password: "Admin123",
        processData: false,
        contentType: "application/json",
        success: function (data) {
            alert("success")+data;
        },
        error: function (xhr, ajaxOptions, thrownError) { //Add these parameters to display the required response
            alert(xhr.status);
            alert(xhr.responseText);
        },
    });
    
    
    
}


