$(document).ready(function() {
   
	$(".book-search").click(function() {
        var url = $(this).data("url");
        url = document.baseURI + url;

        var $button = $(this);
        
        $.get(url, function(data, status) {
    		$button.hide();
    		
    		var borrowedBooks;
    		
    		if (data.length === 0) {
    			borrowedBooks = "This user has not borrowed any books";
    		} else {
    			$.each(data, function( i, book) { 
    				if (i === 0) {
    					borrowedBooks = "<Strong>Title:</Strong> " + book.title + " <Strong>Author:</Strong> " + book.author + " <Strong>ISBN:</Strong> " + book.isbn;
    				} else {
    					borrowedBooks = borrowedBooks + "</br> <Strong>Title:</Strong> " + book.title + " <Strong>Author:</Strong> " + book.author + " <Strong>ISBN:</Strong> " + book.isbn;
    				}
    				
    			});
    		}
    		
        	$button.parent().append(borrowedBooks);
    	});
    });
	
});