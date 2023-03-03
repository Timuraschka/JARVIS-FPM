var createTicketBtn = document.getElementById("create-ticket-btn");
    var overlay = document.getElementById("create-ticket-overlay");
    var closeBtn = document.querySelector(".close-btn");

    createTicketBtn.onclick = function () {
        overlay.style.display = "block";
    }

    closeBtn.onclick = function () {
        overlay.style.display = "none";
    }

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



// Update the ticket object in memory or make an API call to update the ticket

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