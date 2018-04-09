
<div class="footer">
	<input type="hidden" id="shark_state" value="True"></input> <span>
		&copy; 2016 Team BearShark </span> <span> <img
		src="https://pbs.twimg.com/profile_images/2606551173/image.jpg"
		style="width: 80px; height: 80px" id="shark_logo" />
	</span>
</div>

<script>

// This gets called with every mouse move. I give up trying
// to get it working on clicks.
document.addEventListener("mousemove",toggle_shark);		

/**
 * Toggles shark.
 */
function toggle_shark (event) {
	
	// Find modal.
	var modal_elements = document.getElementsByClassName("modal");
	var found = true;	

	// Make sure Modal is displayed.
	for (var i = 0; i < modal_elements.length; i++) {
		var current_modal_style = modal_elements[i].style.getPropertyValue("display");
		if (current_modal_style == 'block')
			found = false;
	}

	// Update shark state.
	if (found == true)
		document.getElementById("shark_state").value = "True";
	else
		document.getElementById("shark_state").value = "False";

	// Toggle shark. This flips it on the X axis.
	var current_state = document.getElementById("shark_state").value;
	var shark_logo = document.getElementById("shark_logo").style;
	if (current_state == "True")
		shark_logo.transform = "scaleX(-1)";
	else
		shark_logo.transform = "scaleX(1)";		
}

</script>

</body>
</head>
