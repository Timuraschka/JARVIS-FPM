// Select all elements with the class "column" and store them in a constant variable called "columns"
const columns = document.querySelectorAll('.column');
// Initialize a variable called "draggedItem" and set it to null
let draggedItem = null;
// Loop over each element in the "columns" array and add event listeners for drag-and-drop functionality
for (const column of columns) {
  column.addEventListener('dragstart', function (event) {
    draggedItem = event.target;
    event.target.style.opacity = .5;
  });
  // When the drag ends, reset the target element's opacity and set "draggedItem" back to null
  column.addEventListener('dragend', function (event) {
    event.target.style.opacity = "";
    draggedItem = null;
  });
  // Prevent the default behavior of the "dragover" event (which is to disallow dropping)
  column.addEventListener('dragover', function (event) {
    event.preventDefault();
  });
  // When an element is dragged over this element, change its background color
  column.addEventListener('dragenter', function (event) {
    event.target.style.backgroundColor = "rgba(0, 0, 0, 0.2)";
  });
  // When an element is dragged out of this element, reset its background color
  column.addEventListener('dragleave', function (event) {
    event.target.style.backgroundColor = "";
  });
  // When an element is dropped onto this element, reset its background color and append the dragged item to it
  column.addEventListener('drop', function (event) {
    event.target.style.backgroundColor = "";
    event.target.appendChild(draggedItem);
  });
}
// Get HTML elements with IDs "create-ticket-btn" and "create-ticket-overlay", and a class "close-btn"
var createTicketBtn = document.getElementById("create-ticket-btn");
var overlay = document.getElementById("create-ticket-overlay");
var closeBtn = document.querySelector(".close-btn");

// When the "create ticket" button is clicked, display the ticket creation overlay
createTicketBtn.onclick = function () {
  overlay.style.display = "block";
}
// When the "close" button is clicked, hide the ticket creation overlay
closeBtn.onclick = function () {
  overlay.style.display = "none";
}
// Get HTML elements with IDs "create-ticket-form" and "add-ticket-overlay"
const createTicketForm = document.getElementById("create-ticket-form");
const addTicketPopup = document.getElementById("add-ticket-overlay");


// Open the popup window when the create ticket button is clicked
createTicketBtn.addEventListener("click", function () {
  addTicketPopup.style.display = "block";
});

// Close the popup window when the close button is clicked
closeBtn.addEventListener("click", function () {
  addTicketPopup.style.display = "none";
});


// Get the form element
const form = document.getElementById('create-ticket-form');

// Get the backlog column
const backlogColumn = document.getElementById('backlog');



// Submit event listener for the form
form.addEventListener('submit', (event) => {
  event.preventDefault();

  // Get the input values from the form
  const assignee = document.getElementById('assignee').value;
  const task = document.getElementById('task').value;
  const priority = document.getElementById('priority').value;

  // Create a new card with the input values
  const newCard = document.createElement('div');
  newCard.classList.add('card');
  newCard.setAttribute('draggable', 'true');

  newCard.innerHTML = `Assignee: ${assignee} <br> Task: ${task} <br> Priority: ${priority}`;

  // Add double click event listener to the card
  newCard.addEventListener('dblclick', () => {
    // Show the detail page
    document.getElementById('detail-page').style.display = 'flex';
    // here: make call to REST API to get ticke-details, hier erweitern und mit den Werten ausm Backend befüllen.
    document.getElementById('ticket-title').value = "Title from Backend";
    document.getElementById('start-date').value = "2023-01-02";
    document.getElementById('end-date').value = "2023-10-02";
    document.getElementById('assignee').value = "Michael";
  });

  // Append the new card to the backlog column
  backlogColumn.insertBefore(newCard, backlogColumn.secondChild);
  // Clear the input fields
  form.reset();

  // Close the popup window
  document.getElementById("create-ticket-overlay").style.display = "none";


})
// Get references to the save and close buttons
const saveButton = document.getElementById('save-btn');
const closeButton = document.getElementById('close-btn');

// Add event listeners to the buttons
saveButton.addEventListener('click', saveDetails);
closeButton.addEventListener('click', closeDetails);

// Save button click handler
function saveDetails() {
  // Get the input values from the form
  const assignee = document.getElementById('assignee').value;
  const task = document.getElementById('task').value;
  const priority = document.getElementById('priority').value;
  console.log("save");
  console.log(document.getElementById('start-date').value);

  // Close the detail page
  closeDetails();
}

// Close button click handler
function closeDetails() {
  // Hide the detail page
  document.getElementById('detail-page').style.display = 'none';
}
function updateProgressValue(value) {
  document.getElementById("progress-value").textContent = value;
}