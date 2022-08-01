const url = "http://localhost:3000"

document.getElementById("welcome-first-name").innerText = "Welcome, " + localStorage.getItem("username");

document.getElementById("approved").onclick=viewApproved;
document.getElementById("pending").onclick=viewPending;
document.getElementById("denied").onclick=viewDenied;
document.getElementById("specialist-view-all-reimbursements").onclick=viewAll;
//document.getElementById("submit-new").onclick=insertNew;

async function viewPending() {

    let response = await fetch(url + "/reimbursement/status/pending")

    if (response.status === 200) {
        let data= await response.text()

        parsed = JSON.parse(data);
        
        emptyTbody();

        

        for (let reimb of parsed) {
            if (localStorage.getItem("roleId") == 2){
                if (reimb.reimbAuthor["ersUsername"] == localStorage.getItem("username")){
                    
                    
                    
                    let row = document.createElement("tr");
                    row.setAttribute("class", "init");
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    let resolvedTBA = document.createElement("td");
                    resolvedTBA.innerText = "--";
                    let receiptTBA = document.createElement("td");
                    receiptTBA.innerText = "--";
                    //let resolvedCell = document.createElement("td");
                    //resolvedCell.innerText = reimb.reimbResolved;
                    //let receiptCell = document.createElement("td");
                    //receiptCell.innerText = reimb.reimbReceipt;
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    let resolverTBA = document.createElement("td");
                    resolverTBA.innerHTML = "--";
                    //let resolverCell = document.createElement("td");
                    //resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    statusCell.innerText = "Pending";
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedTBA);
                    row.appendChild(receiptTBA);
                    row.appendChild(authorCell);
                    row.appendChild(resolverTBA);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
        
                    document.getElementById("specialist-chosen-info").appendChild(row);
                    
                }
            } else if (localStorage.getItem("roleId") == 1) {
            
                    let recordId = reimb.reimbId;
                    let row = document.createElement("tr");
                    row.setAttribute("class", "init");
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    let resolvedTBA = document.createElement("td");
                    resolvedTBA.innerText = "--";
                    let receiptTBA = document.createElement("td");
                    receiptTBA.innerText = "--";
                    //let resolvedCell = document.createElement("td");
                    //resolvedCell.innerText = reimb.reimbResolved;
                    //let receiptCell = document.createElement("td");
                    //receiptCell.innerText = reimb.reimbReceipt;
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    let resolverTBA = document.createElement("td");
                    //resolverTBA.innerHTML = "<button id='resolve-button' onClick=nav(reimb.recordId)>Resolve</button>";
                    //let resolverCell = document.createElement("td");
                    //resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    statusCell.innerText = "Pending";
                    let approveButtonCell = document.createElement("td");
                    let approveButton = document.createElement("button");
                    approveButtonCell.appendChild(approveButton);
                    approveButton.setAttribute("id", "approve-button");
                    approveButton.innerText = "Approve";
                    console.log("record id: " + recordId)
                    approveButton.onclick = function () {
                        finalizeRecord(recordId, statusId=1);
                    }

                    let deniedButtonCell = document.createElement("td");
                    let deniedButton = document.createElement("button");
                    deniedButtonCell.appendChild(deniedButton);
                    deniedButton.setAttribute("id", "deny-button");
                    deniedButton.innerText = "Deny";
                    deniedButton.onclick = () => {
                        finalizeRecord(recordId, statusId = 3);
                    }
                    
                    

                    
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedTBA);
                    row.appendChild(receiptTBA);
                    row.appendChild(authorCell);
                    row.appendChild(resolverTBA);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
                    row.appendChild(approveButtonCell);
                    row.appendChild(deniedButtonCell);

                    

                    document.getElementById("specialist-chosen-info").appendChild(row);
                }
            }
            
    }

}

function currentUser() {
    return localStorage.getItem("userId")
}

function nav(id) {
    location.href="../reimbursement/creation/NewReimbursement.html"
    localStorage.setItem("recordId", id)
    console.log("id: " + id);
}

async function finalizeRecord(reimb, statusId) {
    console.log("FINALIZE REIMBURSEMENT: " + reimb + " FINALIZE STATUS ID: " + statusId);
    let body = {
        "reimbId": reimb,
        "resolverId": localStorage.getItem("userId"),
        "statusId": statusId
        
    }
    let response = await fetch(url + "/reimbursement/finalize", {
        method: "PATCH",
        body: JSON.stringify(body)
    })    

    if (response.status === 200) {
        console.log("successful");
    }
} 

function emptyTbody() {
    if (document.getElementById("specialist-chosen-info").hasChildNodes) {
        let tbody = document.getElementById("specialist-chosen-info")

        while (tbody.firstChild) {
            tbody.removeChild(tbody.firstChild);
        }
    }
}

async function viewDenied() {
    let response = await fetch(url + "/reimbursement/status/denied")

    if (response.status === 200) {
        let data= await response.text()
        
        parsed = JSON.parse(data);

        emptyTbody();
        console.log("Sight-seeing in viewDenied. parsed=[" + parsed +"]")


        document.getElementById("approve-th").hidden = true;
        document.getElementById("deny-th").hidden = true;
        
                    
        if (localStorage.getItem("roleId") == 1) {


        }

        for (let reimb of parsed) {

           
            let row = document.createElement("tr")
            row.setAttribute("class", "init");
            let amtCell = document.createElement("td");
            amtCell.innerHTML = reimb.reimbAmt;
            let submittedCell = document.createElement("td");
            submittedCell.innerText = reimb.reimbSubmitted;
            let resolvedCell = document.createElement("td");
            resolvedCell.innerText = reimb.reimbResolved;
            let receiptCell = document.createElement("td");
            receiptCell.innerHTML = "<a href='view-receiptCell.html'>View receipt</a>";
            //let resolvedCell = document.createElement("td");
            //resolvedCell.innerText = reimb.reimbResolved;
            //let receiptCell = document.createElement("td");
            //receiptCell.innerText = reimb.reimbReceipt;
            let authorCell = document.createElement("td");
            authorCell.innerText = reimb.reimbAuthor["ersUsername"];
            let resolverCell = document.createElement("td");
            resolverCell.innerHTML = reimb.reimbResolver["ersUsername"];
            //let resolverCell = document.createElement("td");
            //resolverCell.innerText = reimb.reimbResolverFk;
            //let statusCell = document.createElement("td");
            //statusCell.innerText = reimb.reimbStatusIdFk;
            let typeCell = document.createElement("td");
            typeCell.innerText = reimb.reimbType;
            let statusCell = document.createElement("td");
            statusCell.innerText = "Denied";
            /*let approveButton = document.createElement("button");
            approveButton.setAttribute("id", "approve-button");
            approveButton.innerText = "Approve";*/

            row.appendChild(amtCell);
            row.appendChild(submittedCell);
            row.appendChild(resolvedCell);
            row.appendChild(receiptCell);
            row.appendChild(authorCell);
            row.appendChild(resolverCell);
            row.appendChild(typeCell);
            row.appendChild(statusCell);

            

            document.getElementById("specialist-chosen-info").appendChild(row);



        }
    }
}

async function viewApproved() {

    document.getElementById("approve-th").hidden = true;
    document.getElementById("deny-th").hidden = true;

    let response = await fetch(url + "/reimbursement/status/approved")

    if (response.status === 200) {
        let data= await response.text()
        
        parsed = JSON.parse(data);

        emptyTbody();

        
        
        let counter = 0;

        for (let reimb of parsed) {

            counter++;
            
            
            let row = document.createElement("tr")
            row.setAttribute("id", "init");
            let amtCell = document.createElement("td");
            amtCell.innerHTML = reimb.reimbAmt;
            let submittedCell = document.createElement("td");
            submittedCell.innerText = reimb.reimbSubmitted;
            let resolvedCell = document.createElement("td");
            resolvedCell.innerText = reimb.reimbResolved;
            let receiptCell = document.createElement("td");
            receiptCell.innerHTML = "<a href='view-receiptCell.html'>View receipt</a>";
            //let resolvedCell = document.createElement("td");
            //resolvedCell.innerText = reimb.reimbResolved;
            //let receiptCell = document.createElement("td");
            //receiptCell.innerText = reimb.reimbReceipt;
            let authorCell = document.createElement("td");
            authorCell.innerText = reimb.reimbAuthor["ersUsername"];
            let resolverCell = document.createElement("td");
            resolverCell.innerHTML = reimb.reimbResolver["ersUsername"];
            //let resolverCell = document.createElement("td");
            //resolverCell.innerText = reimb.reimbResolverFk;
            //let statusCell = document.createElement("td");
            //statusCell.innerText = reimb.reimbStatusIdFk;
            let typeCell = document.createElement("td");
            typeCell.innerText = reimb.reimbType;
            let statusCell = document.createElement("td");
            statusCell.innerText = "Approved";
            /*let approveButton = document.createElement("button");
            approveButton.setAttribute("id", "approve-button");
            approveButton.innerText = "Approve";*/

            row.appendChild(amtCell);
            row.appendChild(submittedCell);
            row.appendChild(resolvedCell);
            row.appendChild(receiptCell);
            row.appendChild(authorCell);
            row.appendChild(resolverCell);
            row.appendChild(typeCell);
            row.appendChild(statusCell);

            
            
            document.getElementById("specialist-chosen-info").appendChild(row);

        }
    }
        
}

async function viewAll () {
    
    document.getElementById("approve-th").hidden = true;
    document.getElementById("deny-th").hidden = true;

    console.log("Entering view All")
    let response = await fetch(url + "/reimbursement/all")
    
    console.log("Sight-seeing. response=[" + response + "]")
    if (response.status == 200){

        let data = await response.text();
    
        parsed = JSON.parse(data);
        console.log("Sight-seeing. parsed=[" + parsed + "]");
        emptyTbody();

        for (let reimb of parsed) {
            if (localStorage.getItem("roleId") == 2){
                if (reimb.reimbAuthor["ersUsername"] == localStorage.getItem("username")){
                    
                    
                    
                    let row = document.createElement("tr");
                    row.setAttribute("class", "init");
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    let resolvedTBA = document.createElement("td");
                    resolvedTBA.innerText = "--";
                    let receiptCell = document.createElement("td");
                    receiptCell.innerHTML = "<a href='view-receiptCell.html'>View receipt</a>";
                    
                    if (reimb.reimbResolved != undefined) {
                        let resolvedCell = document.createElement("td");
                        resolvedCell.innerText = reimb.reimbResolved;
                    }
                    
                    //let receiptCell = document.createElement("td");
                    //receiptCell.innerText = reimb.reimbReceipt;
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    //let resolverTBA = document.createElement("td");
                    //resolverTBA.innerHTML = "--";
                    let resolverCell = document.createElement("td");
                    resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    if (reimb.reunbStatus!= undefined) {
                        statusCell.innerText = reimb.reimbStatus["reimbStatus"];
                    } else {
                        statusCell.innerText = "--";
                    }
                    
                    
                    
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedTBA);
                    row.appendChild(receiptTBA);
                    row.appendChild(authorCell);
                    row.appendChild(resolverTBA);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
        
                    document.getElementById("specialist-chosen-info").appendChild(row);
                    
                }
            } else if (localStorage.getItem("roleId") == 1) {
                    
                    let recordId = reimb.reimbId;
                    let row = document.createElement("tr");
                    row.setAttribute("class", "init");
                    let amtCell = document.createElement("td");
                    amtCell.innerHTML = reimb.reimbAmt;
                    let submittedCell = document.createElement("td");
                    submittedCell.innerText = reimb.reimbSubmitted;
                    //let resolvedTBA = document.createElement("td");
                    //resolvedTBA.innerText = "--";
                    //let receiptTBA = document.createElement("td");
                    //receiptTBA.innerText = "--";

                    let resolvedCell = document.createElement("td");

                    if (reimb.reimbResolved != undefined) {
                        resolvedCell.innerText = reimb.reimbResolved;
                    } else {
                        resolvedCell.innerText = "--";
                    }
                    
                    let receiptCell = document.createElement("td");

                    if (reimb.reimbReceipt){
                        receiptCell.innerText = reimb.reimbReceipt;
                    }
                    
                    
                    let authorCell = document.createElement("td");
                    authorCell.innerText = reimb.reimbAuthor["ersUsername"];
                    //let resolverTBA = document.createElement("td");
                    //resolverTBA.innerHTML = "<button id='resolve-button' onClick=nav(reimb.recordId)>Resolve</button>";
                    let resolverCell = document.createElement("td");
                    
                    if (reimb.reimbResolver != null) {
                        resolverCell.innerText = reimb.reimbResolver["ersUsername"]
                    }
                    else {
                        resolverCell.innerText = "--"
                    }   
                    //resolverCell.innerText = reimb.reimbResolverFk;
                    //let statusCell = document.createElement("td");
                    //statusCell.innerText = reimb.reimbStatusIdFk;
                    let typeCell = document.createElement("td");
                    typeCell.innerText = reimb.reimbType;
                    let statusCell = document.createElement("td");
                    
                    if (reimb.reimbStatus != undefined){
                        statusCell.innerText = reimb.reimbStatus["reimbStatus"];
                    } else {
                        statusCell.innerText = "--"
                    }
                    
                    
                    let approveButtonCell = document.createElement("td");
                    let approveButton = document.createElement("button");
                    approveButtonCell.appendChild(approveButton);
                    approveButton.setAttribute("id", "approve-button");
                    approveButton.innerText = "Approve";
                    console.log("record id: " + recordId)
                    approveButton.onclick = function () {
                        finalizeRecord(recordId, statusId=1);
                    }

                    let deniedButtonCell = document.createElement("td");
                    let deniedButton = document.createElement("button");
                    deniedButtonCell.appendChild(deniedButton);
                    deniedButton.setAttribute("id", "deny-button");
                    deniedButton.innerText = "Deny";
                    deniedButton.onclick = () => {
                        finalizeRecord(recordId, statusId = 3);
                    }
                    
                    

                    
        
                    row.appendChild(amtCell);
                    row.appendChild(submittedCell);
                    row.appendChild(resolvedCell);
                    row.appendChild(receiptCell);
                    row.appendChild(authorCell);
                    row.appendChild(resolverCell);
                    row.appendChild(typeCell);
                    row.appendChild(statusCell);
                    row.appendChild(approveButtonCell);
                    row.appendChild(deniedButtonCell);

                    

                    document.getElementById("specialist-chosen-info").appendChild(row);
                }
            }
            
        }

}
    



