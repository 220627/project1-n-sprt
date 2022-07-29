
const url = "http://localhost:3000"

document.getElementById("welcome-first-name").innerText = "Welcome, " + localStorage.getItem("username");

document.getElementById("approved").onclick=getApproved;
//document.getElementById("pending").onclick=getPending;
//document.getElementById("denied").onclick=getDenied;
//document.getElementById("submit-new").onclick=insertNew;

async function getApproved() {
        console.log("welcome to getApproved");
        let response = await fetch(url + "/reimbursement/status/1");
    console.log(response);
        if (response.status === 200) {
            let data = await response.text();
            console.log("data" + data)

            let whatWeWorkWith = JSON.parse(data);
            console.log("what we workin wit: " + whatWeWorkWith);

            for (let reimb of data) {

            
                console.log(reimb);
                let row = document.createElement("tr")
                let amtCell = document.createElement("td");
                amtCell.innerHTML = reimb.reimb_amount;
                let submittedCell = document.createElement("td");
                submittedCell.innerHTML.reimb_submitted;
    
                row.appendChild(amtCell);
                row.appendChild(submittedCell);
    
                document.getElementById("specialist-chosen-info").appendChild(row);

        }

        


        }
        
    }

