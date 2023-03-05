

// Get the add task button and overlay
const addTaskBtn = document.getElementById("add-task-btn");
const addTaskOverlay = document.getElementById("add-task-overlay");
const taskList = document.getElementById("task-list");
const taskName = document.getElementById("task-name");
const startDate = document.getElementById("start-date");
const endDate = document.getElementById("end-date");
const resources = document.getElementById("task-ressources");
const dependent = document.getElementById("task-dependet");
const costs = document.getElementById("task-costs");
const definition = document.getElementById("task-definition");

let lineCounter = 1;

function addTask() {
    // Retrieve entered data from form
    const name = taskName.value;
    const start = startDate.value;
    const end = endDate.value;
    const res = resources.value;
    const dep = dependent.value;
    const cost = costs.value;
    const def = definition.value;

    // Create a new row in the table with entered data
    const row = taskList.insertRow();
    const line = row.insertCell(0);
    const task = row.insertCell(1);
    const start_date = row.insertCell(2);
    const end_date = row.insertCell(3);
    const ressources = row.insertCell(4);
    const dependent_on = row.insertCell(5);
    const cost_cell = row.insertCell(6);


    line.innerHTML = lineCounter++;
    task.innerHTML = name;
    start_date.innerHTML = start;
    end_date.innerHTML = end;
    ressources.innerHTML = res;
    dependent_on.innerHTML = dep;
    cost_cell.innerHTML = cost + "€";

    // Reset entered data in form
    taskName.value = "";
    startDate.value = "";
    endDate.value = "";
    resources.value = "";
    dependent.value = "";
    costs.value = "";
    definition.value = "";

    // Close the overlay
    closePopup();
}

function closePopup() {
    addTaskOverlay.style.display = "none";
}

addTaskBtn.addEventListener("click", () => {
    addTaskOverlay.style.display = "block";
});
// Find the table and add an event listener
const table = document.getElementById('task-list');
table.addEventListener('click', function (event) {

    // Check if the clicked element is a TD element
    if (event.target.nodeName === 'TD') {

        // Navigate to the parent element that contains the TR element and get the index of the TR element
        const rowIndex = event.target.parentNode.rowIndex;

        // Find the data in the row based on the index
        const data = table.rows[rowIndex].cells;


        // Customize the popup window to display the details
        const popup = document.getElementById('task-details');
        popup.style.display = 'block';
        document.getElementById('task-name-details').innerHTML = data[1].innerHTML;
        document.getElementById('task-start-date-details').innerHTML = data[2].innerHTML;
        document.getElementById('task-end-date-details').innerHTML = data[3].innerHTML;
        document.getElementById('task-ressources-details').innerHTML = data[4].innerHTML;
        document.getElementById('task-dependet-details').innerHTML = data[5].innerHTML;
        document.getElementById('task-costs-details').innerHTML = data[6].innerHTML;
        document.getElementById('task-definition-details').innerHTML = data[7].innerHTML;
    }
});





// Get the current date and format it as a string in the German format
let date = new Date().toLocaleDateString("de-DE");

// Set the inner HTML of an element with ID "date" to the formatted date string
document.getElementById("date").innerHTML = date;


