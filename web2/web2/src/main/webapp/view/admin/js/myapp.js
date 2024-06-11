let coverinput = document.getElementById('cover');
coverinput.addEventListener("change", function(){
	let reader = new FileReader();
	reader.addEventListener('load', function(){
		let image = reader.result
		let showcover = document.getElementById('showcover');
		let img = document.getElementById("cover_image");
		if(img != null)	img.remove();
		showcover.style.backgroundImage = `url(${image})`
		showcover.style.backgroundPosition = 'center'
		showcover.style.backgroundRepeat = 'no-repeat'
		showcover.style.backgroundSize = 'contain'
	})
	reader.readAsDataURL(coverinput.files[0])
	
})

