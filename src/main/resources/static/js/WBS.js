// Get the current date and format it in German (dd.mm.yyyy)
let date = new Date().toLocaleDateString("de-DE");
// Update the HTML element with the id "date" to display the current date
document.getElementById("date").innerHTML = date;

// Get HTML elements for the timeline start, end, and bar
let timelineStart = document.getElementById("timeline-start");
let timelineEnd = document.getElementById("timeline-end");
let timelineBar1 = document.getElementById("timeline-bar-1");

// Get HTML elements for the task list and input fields
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

// Function to add a new task to the task list
function addTask() {
    // Create a new task object with input field values
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
    // Create a new HTML row to display the task in the task list
    let taskRow = `
<tr onclick="showTaskDetails(${tasks.length - 1})">
    <td>${task.name}</td>
    <td>${task.name}</td>
    <td>${task.startDate}</td>
    <td>${task.endDate}</td>
    <td>${task.resources}</td>
    <td>${task.dependent}</td>
    <td>${task.costs}</td>
</tr>`;
    // Add the new task row to the task list HTML element
    taskList.innerHTML += taskRow;
    // Create a new timeline bar HTML element for the new task
    let timelineBarNew = document.createElement("div");
    timelineBarNew.className = "bar";
    timelineBarNew.style.width = `${task.duration * 10}px`;
    timelineBarNew.setAttribute("id", `timeline-bar-${tasks.length}`);

    // Create a label for the timeline bar with the task name
    let labelBarNew = document.createElement("div");
    labelBarNew.className = "label-bar";
    labelBarNew.innerHTML = task.name;
    timelineBarNew.appendChild(labelBarNew);

    // Add the new timeline bar HTML element to the timeline
    let timeline = document.querySelector(".timeline");
    timeline.appendChild(timelineBarNew);

    // Update the timeline end date if the new task end date is later than the current end date
    updateTimelineEnd(new Date(task.endDate));
    // This function displays the details of a task
    function showTaskDetails(index) {
        // Get the task details from the tasks array based on the index
        taskNameDetails.innerHTML = tasks[index].name;
        taskStartDateDetails.innerHTML = tasks[index].startDate;
        taskEndDateDetails.innerHTML = tasks[index].endDate;
        taskResourcesDetails.innerHTML = tasks[index].resources;
        taskDependentDetails.innerHTML = tasks[index].dependent;
        taskCostsDetails.innerHTML = tasks[index].costs;
        taskBeschreibungDetails.innerHTML = tasks[index].beschreibung;

        // Set the "timeline-bar-selected" ID to the selected timeline bar and remove it from the old selected bar
        let timelineBarOld = document.getElementById("timeline-bar-selected");
        if (timelineBarOld) {
            timelineBarOld.removeAttribute("id");
        }
        let timelineBars = document.getElementsByClassName("bar");
        timelineBars[index].setAttribute("id", "timeline-bar-selected");

        // Show the task details window
        document.getElementById("task-details").style.display = "block";
    }

    // This function calculates the difference in days between two dates
    function getDayDifference(date1, date2) {
        let timeDiff = Math.abs(date2.getTime() - date1.getTime());
        let dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
        return dayDiff;
    }

    // This function updates the timeline end date and width of the timeline bar if a task end date is after the current timeline end date
    function updateTimelineEnd(date) {
        let currentTimelineEnd = new Date(timelineEnd.innerHTML);
        if (date > currentTimelineEnd) {
            timelineEnd.innerHTML = date.toISOString().slice(0, 10);
            timelineBar1.style.width = `${getDayDifference(new Date(timelineEnd.innerHTML), new Date(timelineStart.innerHTML)) * 10}px`;
        }
    }

    // This function opens the popup
    function openPopup() {
        document.getElementById("popup").style.display = "block";
    }

    // This function closes the popup
    function closePopup() {
        document.getElementById("popup").style.display = "none";
    }
}
