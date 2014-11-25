function refPubTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}

function refpub (attributes) {
	this.endpoint = attributes['endpoint'];
	this.solrEndpoint = attributes['solr_endpoint'];
	this.countOfMaxElements = attributes['pagination_count'] || "50";



	this.init = function() {
		$.support.cors = true;
		window['scrollbinded'] = false;
		var self = this;
		$body = $("body");
		$.ajax({
		      type: 'GET',
		      url: self.endpoint + '/concept',
		      crossDomain: true,
		      cache: false,
		      dataType : 'json',
		      beforeSend: function() { $body.addClass("loading"); },
		      success: function(data,textStatus,jqXHR) {
		      	$body.removeClass("loading");
		      	var selfSucc = self;
		      	var nav = $("#nav-inner");
		      	$.each(data['concept'], function(index, value) {
		      		var selfEach = selfSucc;
		      		var nameEntry = value['link'][0]['rel'];
		      		window['refpub_' + nameEntry] = value['link'][0]['href'];
  					var entry = $("<button></button>").attr("class", "primary-button pure-button").attr("id", nameEntry).text(nameEntry).click(function() {
  						$("#list").scrollTop(0);
  						var selfClick = selfEach;
  						var self = this;
  						selfClick.getObjects(window['refpub_' + self.id] + "?count=" + selfClick.countOfMaxElements, true);
  					});
		      		
		      		nav.append(entry);
				});
		      }
		    });
		this.initFreeText(this, $('#nav'));
	};

	this.initFreeText = function(self, div) {
		var freetext = $("<input />").attr("type", "text").attr("id", "autocomplete").attr("placeholder", "Free Text Search");
		div.append(freetext);
		freetext.autocomplete({
            serviceUrl: self.solrEndpoint + '/select?wt=json',
            paramName: 'q=text:',
            onSelect: function (suggestion) {
                self.getSingle(suggestion.data);
            },
            transformResult: function(response) {
                var x=0;
                var out={};
                var docs = response.response.docs;
                for (var i=0; i < docs.length; i++) {
                    var doc = docs[i];
                    if (doc['full_name_e'] != undefined && doc['full_name_e'] != null) {
                        out[x] = {value: doc.url, data: doc['full_name_e']}; 
                        x=x+1;
                    } else if (doc['long_name_e'] != undefined && doc['long_name_e'] != null) {
                        out[x] = {value: doc.url, data: doc['long_name_e']}; 
                        x=x+1;
                    } else if (doc['official_name_e'] != undefined && doc['official_name_e'] != null) {
                        out[x] = {value: doc.url, data: doc['name_e']}; 
                        x=x+1;
                    } else if (doc['name_e'] != undefined && doc['name_e'] != null) {
                        out[x] = {value: doc.url, data: doc['name_e']}; 
                        x=x+1;
                    }
                }
                return {
                    suggestions: $.map(out, function(dataItem) {
                        return { value: dataItem.data, data: dataItem.value };
                    })
                };
            }
        });
		ftself = self;
		freetext.keypress(function(e) {
  			if (!e) e = window.event;
		    var keyCode = e.keyCode || e.which;
		    if (keyCode == '13'){
		      ftself.getObjectsSolr(ftself, freetext.val());
		    }
		});
	};

	this.getObjects = function(endpoint, clean) {
		window['refpub_retrievingdata'] = true;
		var self = this;
		$body = $("body");
		if (clean == undefined) { clean = false; }
		$.ajax({
			type: 'GET',
		    url: endpoint,
		    dataType : 'json',
		    beforeSend: function() { $body.addClass("loading"); },	
		    success : function(data) {
		    	$body.removeClass("loading");
		    	var selfSuccess = self;

		    	if (data['link'] != undefined && data['link'].length > 0) {
		    		window['refpub_linknext'] = null;
		    		for (var i = 0; i < data['link'].length; i++) {
		    			if (data['link'][i]['rel'].toLowerCase() == "next") {
		    				window['refpub_linknext'] = data['link'][i]['href'];
		    			}
		    		}
		    	}

		    	var list = $( "#list" );
		    	if (clean) {
		    		list.empty();
		    	}
		    	$.each(data['concept'], function(index, value) {
		    		var selfEach = selfSuccess;
		    		var link = value['link'][0]['href'];
		    		var newObjId = 'refpub_obj_' + selfEach.generateUUID();
		    		window[newObjId] = link;
		    		var nameFinal = "";

		    		if (value['multilingualLongName']['value'] != null) {
			    		var length = value['multilingualLongName']['value'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualLongName']['value'][i]['lang'] == 'en') {
								nameFinal = value['multilingualLongName']['value'][i]['value'];
							}
						}
					} else if (value['multilingualFullName']['value'] != null) {
						var length = value['multilingualFullName']['value'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualFullName']['value'][i]['lang'] == 'en') {
								nameFinal = value['multilingualFullName']['value'][i]['value'];
							}
						}	
					} else if (value['multilingualName']['value'] != null) {
						var length = value['multilingualName']['value'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualName']['value'][i]['lang'] == 'en') {
								nameFinal = value['multilingualName']['value'][i]['value'];
							}
						}	
					} else if (value['multilingualShortDescription']['value'] != null) {
						var length = value['multilingualLongName']['value'].length;   
						for (var i = 0; i < length; i++) {
							if (value['multilingualShortDescription']['value'][i]['lang'] == 'en') {
								nameFinal = value['multilingualShortDescription']['value'][i]['value'];
							}
						}	
					}
					var name = $("<h5></h5>").attr("class", "email-name").text(nameFinal);

					var divA = $("<div></div>").attr("class", "email-item email-item-unread pure-g").attr("id", newObjId).click(function() {
  						var selfClick = selfEach;
  						var self = this;
  						selfClick.getSingle(window[self.id]);
  					});

					var divB = $("<div></div>").attr("class", "pure-u-3-4");

					divA.append(divB.append(name));

					var list = $( "#list" );
					if (nameFinal != null && nameFinal != "" && nameFinal != " ") {
						list.append(divA);
					}
		    	});
				if (!window['scrollbinded']) {
					self.bindScrollToDiv(self, $("#list"));
				}
				window['refpub_retrievingdata'] = false;
		    }
		});
	};

	this.getObjectsSolr = function(self, value) {
		var selfjson = self
		$body = $("body");
		var url = self.solrEndpoint +  '/select?wt=json&q=text:' + encodeURIComponent(value) + "*";
		$.ajax({
			type: 'GET',
			url: url,
		    dataType : 'json',
			beforeSend: function() { $body.addClass("loading"); },
			success : function(data) {
				$body.removeClass("loading"); 
				var list = $( "#list" );
		    	list.empty();
				var docs = data.response.docs;

				for (var i = 0; i < docs.length; i++) {
					var newObjId = 'refpub_obj_' + i;
		    		window[newObjId] = docs[i]['url'];
					var nameFinal = null;
					if (docs[i]['full_name_e'] != undefined && docs[i]['full_name_e'] != null && docs[i]['full_name_e'] != "" && docs[i]['full_name_e'] != " ") {
						nameFinal = docs[i]['full_name_e'];
					} else if (docs[i]['long_name_e'] != undefined && docs[i]['long_name_e'] != null && docs[i]['long_name_e'] != "" && docs[i]['long_name_e'] != " ") {
						nameFinal = docs[i]['long_name_e'];
					} else if (docs[i]['name_e'] != undefined && docs[i]['name_e'] != null && docs[i]['name_e'] != "" && docs[i]['name_e'] != " ") {
						nameFinal = docs[i]['name_e'];
					}
					if (nameFinal != null) {
						var name = $("<h5></h5>").attr("class", "email-name").text(nameFinal);
						var divA = $("<div></div>").attr("class", "email-item email-item-unread pure-g").attr("id", 'refpub_obj_' + i).click(function() {
	  						var selfClick = selfjson;
	  						var self = this;
	  						selfClick.getSingle(window[self.id]);
	  					});	
	  					var divB = $("<div></div>").attr("class", "pure-u-3-4");
						divA.append(divB.append(name));
						var list = $( "#list" );
						list.append(divA);
					}
				}
			},
			dataType: 'jsonp',
	  		jsonp: 'json.wrf'
		});
	};

	this.getSingle = function(endpoint) {
		var main = $( "#main" );
		$body = $("body");
		main.empty();
		var self = this;
		$.ajax({
			type: 'GET',
		    url: endpoint,
			dataType : 'json',
		    beforeSend: function() { $body.addClass("loading"); },	
		    success : function(data) {
		   		$body.removeClass("loading"); 
		    	selfJson = self;
		    	var content = $("<div></div>").attr("class", "email-content");
		    	var html = "<div class='topResContainer'><div class='topResLeft'>Source&nbsp;<a href='" + endpoint + "' target='_blank'>JSON</a>/<a href='" + endpoint.replace(/\/json/g, "/xml") + "' target='_blank'>XML</a></div>";
		    	html += "<div class='topResRight'>Concept Type: <span class='enphasy'>" + data['link'][0]['rel'] + "</span></div>";
		    	html += "</div><hr/>";
		    	if (data['multilingualName'] != null && Object.keys(data['multilingualName']).length > 0 && data['multilingualName']['value'] != null) {
		    		var tHtml = "";
		    		for (var i = 0; i < data['multilingualName']['value'].length ; i++) {
		    			var l = data['multilingualName']['value'][i]['lang'];
		    			var v = data['multilingualName']['value'][i]['value'];
		    			if (refPubTrim(v) != "") {
		    				tHtml += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    			}
		    		}
		    		if (tHtml != "") {
		    			html += "<div class='mainDivisors'><div class='h2div'>Name</div></div>" + tHtml;
		    		}
		    	}
		    	if (data['multilingualFullName'] != null && Object.keys(data['multilingualFullName']).length > 0 && data['multilingualFullName']['value'] != null) {
		    		var tHtml = "";
		    		for (var i = 0; i < data['multilingualFullName']['value'].length ; i++) {
		    			var l = data['multilingualFullName']['value'][i]['lang'];
		    			var v = data['multilingualFullName']['value'][i]['value'];
		    			if (refPubTrim(v) != "") {
		    				tHtml += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    			}
		    		}
		    		if (tHtml != "") {
		    			html += "<div class='mainDivisors'><div class='h2div'>Full Name</div></div>" + tHtml;
		    		}
		    	}
		    	if (data['multilingualLongName'] != null && Object.keys(data['multilingualLongName']).length > 0 && data['multilingualLongName']['value'] != null) {
		    		var tHtml = "";
		    		for (var i = 0; i < data['multilingualLongName']['value'].length ; i++) {
		    			var l = data['multilingualLongName']['value'][i]['lang'];
		    			var v = data['multilingualLongName']['value'][i]['value'];
		    			if (refPubTrim(v) != "") {
		    				tHtml += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    			}
		    		}
		    		if (tHtml != "") {
		    			html += "<div class='mainDivisors'><div class='h2div'>Long Name</div></div>" + tHtml;
		    		}
		    	}
		    	if (data['multilingualShortDescription'] != null && Object.keys(data['multilingualShortDescription']).length > 0 && data['multilingualShortDescription']['value'] != null) {
		    		var tHtml = "";
		    		for (var i = 0; i < data['multilingualShortDescription']['value'].length ; i++) {
		    			var l = data['multilingualShortDescription']['value'][i]['lang'];
		    			var v = data['multilingualShortDescription']['value'][i]['value'];
		    			if (refPubTrim(v) != "") {
		    				tHtml += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    			}
		    		}
		    		if (tHtml != "") {
		    			html += "<div class='mainDivisors'><div class='h2div'>Short Description</div></div>" + tHtml;
		    		}
		    	}
		    	if (data['attr'] != null && data['attr']['value'] != null && data['attr']['value'].length > 0) {
		    		var tHtml = "";
		    		for (var i = 0; i < data['attr']['value'].length ; i++) {
		    			var l = data['attr']['value'][i]['name'];
		    			var v = data['attr']['value'][i]['value'];
		    			if (refPubTrim(v) != "") {
		    				tHtml += "<div class='mainDivisors'><div class='header'>" + l + ":</div><div class='valH'>" + v + "</div></div>";
		    			}
		    		}
		    		if (tHtml != "") {
		    			html += "<div class='mainDivisors'><div class='h2div'>Attributes</div></div>" + tHtml;
		    		}
		    	}

		    	if (data['hierarchy'] != null && Object.keys(data['hierarchy']).length > 0) {
		    		if (data['hierarchy']['parents'][0] != undefined || data['hierarchy']['children'][0] != undefined) {
		    			var parentsLength = 0;
		    			var childrenLength = 0;
		    			var printHierarchy = false;
		    			if (data['hierarchy']['parents'][0]['parent'] != undefined) {
		    				parentsLength = data['hierarchy']['parents'][0]['parent'].length;
		    				printHierarchy = true;
		    			}
		    			if (data['hierarchy']['childrens'][0]['child'] != undefined) {
		    				childrenLength = data['hierarchy']['childrens'][0]['child'].length;
		    				printHierarchy = true;
		    			}
			    		if (printHierarchy) {
				    		html += "<div class='mainDivisors'><div class='h2div'>Hierarchy</div></div>";
				    		var linksToShow = {};
				    		if (parentsLength > 0) {
				    			html += "<div class='h3div'>Parents";
				    			for (var i=0; i<parentsLength; i++) {
				    				var parent = data['hierarchy']['parents'][0]['parent'][i];
				    				var linkref = null;
				    				if (parent['link'][0]['href'] != undefined) {
				    					linkref = parent['link'][0]['href'];
				    				}
				    				var label = "";
				    				if (parent['value'] != undefined && parent['value'][0] != undefined) {
				    					label = parent['value'][0]['name'];
				    					html += "<div class=\"hblock\"><div class=\"h4div\">" + label + "</div>";
					    				for (var j=0; j<parent['value'].length; j++) {
					    					if (refPubTrim(parent['value'][j]['value']) != "") {
						    					var uuid = selfJson.generateUUID();
						    					var lang = "";
						    					if (parent['value'][j]['lang'] != undefined) { lang = parent['value'][j]['lang']; }
						    					html += "<div class=\"header\">" + parent['value'][j]['type'] + " " + lang + ":</div>";
						    					html += "<div class=\"valH2\" id='"+uuid+"'></div>";
						    					linksToShow[uuid] = {name: parent['value'][j]['value'], link: linkref, uuid: uuid};
						    				}
					    				}
					    				html += "</div>";
				    				}
				    			
				    			}
				    			html += "</div>";
				    		}

				    		if (childrenLength > 0) {
				    			html += "<div class='h3div'>Childrens";	
				    			for (var i=0; i<childrenLength; i++) {
				    				var child = data['hierarchy']['childrens'][0]['child'][i];
				    				var linkref = null;
				    				if (child['link'][0]['href'] != undefined) {
				    					linkref = child['link'][0]['href'];
				    				}
				    				var label = "";
				    				if (child['value'] != undefined && child['value'][0] != undefined) {
				    					label = child['value'][0]['name'];
				    					html += "<div class=\"hblock\"><div class=\"h4div\">" + label + "</div>";
					    				for (var j=0; j<child['value'].length; j++) {
					    					if (refPubTrim(child['value'][j]['value']) != "") {
						    					var uuid = selfJson.generateUUID();
						    					var lang = "";
						    					if (child['value'][j]['lang'] != undefined) { lang = child['value'][j]['lang']; }
						    					html += "<div class=\"header\">" + child['value'][j]['type'] + " " + lang + ":</div>";
						    					html += "<div class=\"valH2\" id='"+uuid+"'></div>";
						    					linksToShow[uuid] = {name: child['value'][j]['value'], link: linkref, uuid: uuid};
					    					}
					    				}
					    				html += "</div>";
					    			}
				    			}
				    			html += "</div>";
				    		}
				    	}
		    		}
		    	}

		    	content.html(html);
		    	main.append(content);
				window['refpub_linkksh'] = {};
		    	for (var k in linksToShow) {
		    		var divH = $("#" + k);
		    		window['refpub_linkksh'][k] = linksToShow[k]['link'];
		    		divH.click(function() {
  						var selfClick = selfJson;

  						var self = this;
  						selfClick.getSingle(window['refpub_linkksh'][self.id]);
  					});
  					divH.html(linksToShow[k]['name']);
		    	}
		    }});
	};



	this.generateUUID = function() {
    	var d = new Date().getTime();
    	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        	var r = (d + Math.random()*16)%16 | 0;
        	d = Math.floor(d/16);
        	return (c=='x' ? r : (r&0x7|0x8)).toString(16);
    	});
    	return uuid;
	};


	this.bindScrollToDiv = function(self, div) {
		div.bind('scroll', function() {
			window['scrollbinded'] = true;
			if($(this).scrollTop() + $(this).innerHeight() >= this.scrollHeight) {
			    if (window['refpub_linknext'] != null) {
			    	if (!window['refpub_retrievingdata']) {
		        		self.getObjects(window['refpub_linknext'], false);
		        	}
		        }
		    }
		});
	};
}