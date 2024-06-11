const clickAreas = document.querySelectorAll('.star div')
const stars = document.querySelectorAll('.star i')
let starCount = 0
clickAreas.forEach((area, i) => {
	area.addEventListener('click', () => {
		stars.forEach(star => star.className = 'bx bx-star')
		starCount = (i + 1) / 2;
		for(let j = 0 ; j < starCount ; j++){
			if(starCount - j == 0.5){
				stars[j].className = "bx bxs-star-half";	
			}else{
				stars[j].className = "bx bxs-star";	
			}
		}
	})
})

function addReview(bookid){
	let context = window.location.pathname;
	context = context.substring(0, context.indexOf("/", 2))
	$.ajax({
		url : context + '/user/review',
		type : 'post',
		data : {
			bookid : bookid,
			star : 	starCount,
			content : document.querySelector('#content').value
		},	
		success : function(response){
			if(response.msg == 'successAddReview'){
				location.reload();
			}
		}, 
		error : function(er){
			console.log(er)
		}
	});
}