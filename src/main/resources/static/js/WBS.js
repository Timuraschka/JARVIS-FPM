// Get the current date and format it as a string in the German format
let date = new Date().toLocaleDateString("de-DE");

// Set the inner HTML of an element with ID "date" to the formatted date string
document.getElementById("date").innerHTML = date;

// Get elements with specific IDs and assign them to variables
let timelineStart = document.getElementById("timeline-start");
let timelineEnd = document.getElementById("timeline-end");
let timelineBar1 = document.getElementById("timeline-bar-1");

let taskList = document.getElementById("task-list");
let taskName = document.getElementById("task-name");
let taskStartDate = document.getElementById("task-start-date");
let taskEndDate = document.getElementById("task-end-date");
let taskResources = document.getElementById("task-resources");
let taskDependent = document.getElementById("task-dependent");
let taskCosts = document.getElementById("task-costs");
let taskBeschreibung = document.getElementById("task-definition");
let taskNameDetails = document.getElementById("task-name-details");
let taskStartDateDetails = document.getElementById("task-start-date-details");
let taskEndDateDetails = document.getElementById("task-end-date-details");
let taskResourcesDetails = document.getElementById("task-resources-details");
let taskDependentDetails = document.getElementById("task-dependent-details");
let taskCostsDetails = document.getElementById("task-costs-details");
let taskBeschreibungDetails = document.getElementById("task-definition-details");

// Initialize an empty array to store tasks
let tasks = [];

// Define a function to add a new task to the task list and timeline
function addTask() {
    // Create a new task object with properties based on user input
    let task = {
        name: taskName.value,
        startDate: taskStartDate.value,
        endDate: taskEndDate.value,
        resources: taskResources.value,
        dependent: taskDependent.value,
        costs: taskCosts.value,
        beschreibung: taskBeschreibung.value
    };
    // Add the new task to the tasks array
    tasks.push(task);

    // Create a new HTML row for the task list with the task details
    let taskRow = `
<tr onclick="showTaskDetails(${tasks.length - 1})">
    <td>${task.name}</td>
    <td>${task.name}</td>
    <td>${task.startDate}</td>
    <td>${task.endDate}</td>
    <td>${task.resources}</td>
    <td>${task.dependent}</td>
    <td>${task.costs}</td>
</tr>
`;
    // Add the task row to the task list
    taskList.innerHTML += taskRow;

    // Create a new timeline bar element for the task with a label
    let timelineBarNew = document.createElement("div");
    timelineBarNew.className = "bar";
    timelineBarNew.style.width = `${task.duration * 10}px`;
    timelineBarNew.setAttribute("id", `timeline-bar-${tasks.length}`);

    let labelBarNew = document.createElement("div");
    labelBarNew.className = "label-bar";
    labelBarNew.innerHTML = task.name;
    timelineBarNew.appendChild(labelBarNew);

    // Add the timeline bar element to the timeline
    let timeline = document.querySelector(".timeline");
    timeline.appendChild(timelineBarNew);

    // Update the timeline end date if necessary
    updateTimelineEnd(new Date(task.endDate));
}
// Define a function to display task details in a popup window
function showTaskDetails(index) {
    // Set the inner HTML of elements in the task details popup with task details
    taskNameDetails.innerHTML = tasks[index].name;
    taskStartDateDetails.innerHTML = tasks[index].startDate;
    taskEndDateDetails.innerHTML = tasks[index].endDate;
    taskResourcesDetails.innerHTML = tasks[index].resources;
    taskDependentDetails.innerHTML = tasks[index].dependent;
    taskCostsDetails.innerHTML = tasks[index].costs;
    taskBeschreibungDetails.innerHTML = tasks[index].beschreibung;

    // Remove the "timeline-bar-selected" ID from any previously selected task
    let timelineBarOld = document.getElementById("timeline-bar-selected");
    if (timelineBarOld) {
        timelineBarOld.removeAttribute("id");
    }
    // Set the "timeline-bar-selected" ID for the selected task
    let timelineBars = document.getElementsByClassName("bar");
    timelineBars[index].setAttribute("id", "timeline-bar-selected");
    // sichtbar machen des Task Details-Fensters
    document.getElementById("task-details").style.display = "block";
}

// Make the task details window visible
function ShowTaskDetails() {
    document.getElementById("task-details").style.display = "block";
}

function showTaskDetails(index) {
    taskNameDetails.innerHTML = tasks[index].name;
    taskStartDateDetails.innerHTML = tasks[index].startDate;
    taskEndDateDetails.innerHTML = tasks[index].endDate;
    taskResourcesDetails.innerHTML = tasks[index].resources;
    taskDependentDetails.innerHTML = tasks[index].dependent;
    taskCostsDetails.innerHTML = tasks[index].costs;
    taskBeschreibungDetails.innerHTML = tasks[index].beschreibung;


    let timelineBarOld = document.getElementById("timeline-bar-selected");
    if (timelineBarOld) {
        timelineBarOld.removeAttribute("id");
    }
    let timelineBars = document.getElementsByClassName("bar");
    timelineBars[index].setAttribute("id", "timeline-bar-selected");
}


function getDayDifference(date1, date2) {
    let timeDiff = Math.abs(date2.getTime() - date1.getTime());
    let dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return dayDiff;
}

function updateTimelineEnd(date) {
    let currentTimelineEnd = new Date(timelineEnd.innerHTML);
    if (date > currentTimelineEnd) {
        timelineEnd.innerHTML = date.toISOString().slice(0, 10);
        timelineBar1.style.width = `${getDayDifference(new Date(timelineEnd.innerHTML), new Date(timelineStart.innerHTML)) * 10}px`;
    }
}
function openPopup() {
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("popup").style.display = "none";
}