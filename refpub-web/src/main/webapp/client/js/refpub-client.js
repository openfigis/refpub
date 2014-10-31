function refpub (attributes) {
	this.endpoint = attributes['endpoint'];


	this.init = function() {
		var self = this;
		$.ajax({
		      type: 'GET',
		      url: 'http://localhost:8080/refpub-web/rest/concept',
		      crossDomain: true,
		      cache: false,
		      success: function(data) {
		      	var selfSucc = self;
		      	$.each(data['concepts'], function(index, value) {
		      		var selfEach = selfSucc;
		      		var nameEntry = value['links'][0]['rel'];
		      		window['refpub_' + nameEntry] = value['links'][0]['value'];
  					var entry = $("<button></button>").attr("class", "primary-button pure-button").attr("id", nameEntry).text(nameEntry).click(function() {
  						var selfClick = selfEach;

  						var self = this;

  						selfClick.getObjects(window['refpub_' + self.id]);
  						//alert (window['refpub_' + self.id]);
  					});
		      		var nav = $("#nav-inner");
		      		nav.append(entry);
				});
		      }
		    });
	}

	this.getObjects = function(endpoint) {
		var self = this;
		$.ajax({
			type: 'GET',
		    url: endpoint + "?count=200",	
		    success : function(data) {
		    	var selfSuccess = self;
		    	var list = $( "#list" );
		    	list.empty();
		    	var counter = 0;
		    	$.each(data['concepts'], function(index, value) {
		    		var selfEach = selfSuccess;
		    		var link = value['links'][0]['value'];
		    		var newObjId = 'refpub_obj_' + counter;
		    		window[newObjId] = link;

		    		var mainDiv = $("<div></div>").attr("class", "email-name");
		    		var name = $("<h5></h5>").attr("class", "email-name");

		    		var nameFinal = "";

		    		if (value['multilingualName']['values'] != null) {
			    		var length = value['multilingualName']['values'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualName']['values'][i]['lang'] == 'en') {
								nameFinal = value['multilingualName']['values'][i]['value'];
							}
						}
					} else if (value['multilingualFullName']['values'] != null) {
						var length = value['multilingualFullName']['values'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualFullName']['values'][i]['lang'] == 'en') {
								nameFinal = value['multilingualFullName']['values'][i]['value'];
							}
						}	
					} else if (value['multilingualLongName']['values'] != null) {
						var length = value['multilingualLongName']['values'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualLongName']['values'][i]['lang'] == 'en') {
								nameFinal = value['multilingualLongName']['values'][i]['value'];
							}
						}	
					} else if (value['multilingualShortDescription']['values'] != null) {
						var length = value['multilingualLongName']['values'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualShortDescription']['values'][i]['lang'] == 'en') {
								nameFinal = value['multilingualShortDescription']['values'][i]['value'];
							}
						}	
					}
					var name = $("<h5></h5>").attr("class", "email-name").text(nameFinal);

					var divA = $("<div></div>").attr("class", "email-item email-item-unread pure-g").attr("id", 'refpub_obj_' + counter).click(function() {
  						var selfClick = selfEach;

  						var self = this;

  						selfClick.getSingle(window[self.id]);
  						//alert (window['refpub_' + self.id]);
  					});
					var divB = $("<div></div>").attr("class", "pure-u-3-4");

					divA.append(divB.append(name));

					var list = $( "#list" );
					if (nameFinal != null && nameFinal != "" && nameFinal != " ") {
						list.append(divA);
					}

		    		counter++;
		    	});
		    }
		});
	}

	this.getSingle = function(endpoint) {
		var main = $( "#main" );
		main.empty();
		//main.append($("<div></div>").text(endpoint));
		var self = this;
		$.ajax({
			type: 'GET',
		    url: endpoint,	
		    success : function(data) {
		    	selfJson = self;
		    	var content = $("<div></div>").attr("class", "email-content");
		    	var html = "";
		    	if (data['multilingualName'] != null && Object.keys(data['multilingualName']).length > 0) {
		    		html += "<div class='mainDivisors'><div class='h2div'>Multilingual Name</div></div>"
		    		for (var i = 0; i < data['multilingualName'].values.length ; i++) {
		    			var l = data['multilingualName']['values'][i]['lang'];
		    			var v = data['multilingualName']['values'][i]['value'];
		    			html += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    		}
		    	}
		    	if (data['multilingualFullName'] != null && Object.keys(data['multilingualFullName']).length > 0) {
		    		html += "<div class='mainDivisors'><div class='h2div'>Multilingual Full Name</div></div>"
		    		for (var i = 0; i < data['multilingualFullName'].values.length ; i++) {
		    			var l = data['multilingualFullName']['values'][i]['lang'];
		    			var v = data['multilingualFullName']['values'][i]['value'];
		    			html += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    		}
		    	}
		    	if (data['multilingualLongName'] != null && Object.keys(data['multilingualLongName']).length > 0) {
		    		html += "<div class='mainDivisors'><div class='h2div'>Multilingual Long Name</div></div>"
		    		for (var i = 0; i < data['multilingualLongName'].values.length ; i++) {
		    			var l = data['multilingualLongName']['values'][i]['lang'];
		    			var v = data['multilingualLongName']['values'][i]['value'];
		    			html += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    		}
		    	}
		    	if (data['multilingualShortDescription'] != null && Object.keys(data['multilingualShortDescription']).length > 0) {
		    		html += "<div class='mainDivisors'><div class='h2div'>Multilingual Short Description</div></div>"
		    		for (var i = 0; i < data['multilingualShortDescription'].values.length ; i++) {
		    			var l = data['multilingualShortDescription']['values'][i]['lang'];
		    			var v = data['multilingualShortDescription']['values'][i]['value'];
		    			html += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    		}
		    	}
		    	if (data['hierarchy'] != null && Object.keys(data['hierarchy']).length > 0) {

		    		html += "<div class='mainDivisors'><div class='h2div'>Hierarchy</div></div>";
		    		for (var i = 0; i < data['hierarchy'].values.length ; i++) {
		    			html += "&nbsp;&nbsp;<div class='headerH'>" + data['hierarchy'].values[i]['names'][0] + "</div><hr />";
		    			for (var j = 0; j < data['hierarchy'].values[i]['values'].length ; j++) {
		    				html += "<div class='mainDivisors'><div class='header'>" + data['hierarchy'].values[i]['values'][j]['name'] + ":</div>";
		    				html += "<div class='valH'>" + data['hierarchy'].values[i]['values'][j]['value'] + "</div></div>";
		    			}
		    		}
		    	}

		    	content.html(html);
		    	main.append(content);
		    }});
	};

/*

<div class="pure-u-3-4">
    <h5 class="email-name">Yahoo! Finance</h5>
    <h4 class="email-subject">How to protect your finances from winter storms</h4>
    <p class="email-desc">
        Mauris tempor mi vitae sem aliquet pharetra. Fusce in dui purus, nec malesuada mauris.
    </p>
</div>
*/
}