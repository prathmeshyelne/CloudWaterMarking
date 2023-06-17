 
<!DOCTYPE html>
<%@page import="services.RC6"%>
<%@page import="models.GetStateNCities"%>
<%@page import="models.States"%>
<%@page import="java.util.List"%>
<html lang="zxx">

<head>
	<title>Enhancing Data Security Using Digital Watermarking </title>
	<!-- for-mobile-apps -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	  <meta charset="utf-8" />
	<meta name="keywords" content="Attainment Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
	<script> 
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- //for-mobile-apps -->
	 <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
	<!--banner slider  -->
	<link href="css/JiSlider.css" rel="stylesheet">
	<!-- //banner-slider -->
		<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" property="" />
	<link href="css/font-awesome.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="//fonts.googleapis.com/css?family=Oswald:400,500,600,700" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">

<script language="Javascript" type="text/javascript">
 

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequest(st) {
   // st=document.frm.state.value;
   
    http.open('get', 'Cities?state=' + st);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>
</head>

<body>
<%
RC6 obj1=new RC6();
byte[] b=obj1.encrypt("megha".getBytes(),"1121212121212121".getBytes());
byte[]d=obj1.decrypt(b,"1121212121212121".getBytes());
String dec=new String(d);
System.out.println("enc="+dec);
%>
	<!-- header -->
	<section class="w3layouts-header py-2">
		<div class="container">
			  <!-- header -->
        <header>
        <h1>
                        <a class="navbar-brand" href="index.jsp">
                          Enhancing Data Security Using Digital Watermarking     
                        </a>
                    </h1> 
                <nav class="navbar navbar-expand-lg navbar-light bg-gradient-secondary">
                    
                    <button class="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                     
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-lg-auto text-center">
                            <li class="nav-item active  mr-3">
                                <a class="nav-link" href="index.jsp">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item  mr-3">
                                <a class="nav-link" href="#login">Login</a>
                            </li>
							 <li class="nav-item  mr-3">
                                <a class="nav-link" href="#registration">Registration</a>
                            </li>
                            <!-- 
                            <li class="nav-item dropdown mr-3">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    Pages
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="gallery.html">Gallery</a>
									 <a class="dropdown-item" href="typo.html">Typography</a>
                                </div>
                            </li>
                            <li class="nav-item mr-3">
                                <a class="nav-link" href="contact.html">contact</a>
                            </li> -->
                        </ul>
                    </div>
                </nav>
        </header>
        <!-- //header -->
		</div>
	</section>
	<!-- //header -->
	<!-- banner -->
	<section class="banner-silder">
		<div id="JiSlider" class="jislider">
			<ul>
				<li>
					<div class="banner-top banner-top1">
						<div class="container">
							<div class="banner-info info2">
								 </div>
						</div>
					</div>
				</li>
				<li>
					<div class="banner-top banner-top2">
						<div class="container">
							<div class="banner-info bg3 info2">
							 	</div>
						</div>
					</div>
				</li>
				<li>
					<div class="banner-top banner-top3">
						<div class="container">
							<div class="banner-info bg3">
							 	</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		</section>
		<!-- //banner -->
		<!-- banner bottom -->
		<section class="banner-btm">
			<div class="container">
				<div class="row banner-bottom-main bg-white">
						<div class="col-md-4 banner-grid2">
							<div class="banner-subg1">
								<h3 class="mt-3"><span class="fas fa-user-secret pr-3" aria-hidden="true"></span> Watermarking Techniques for Leakage Detection</h3>
							 </div>
						</div>
						<div class="col-md-4 banner-grid2">
							<div class="banner-subg1">
								<h3 class="mt-3"><span class="fas fa-user-secret pr-3" aria-hidden="true"></span> RC6 Algorithm for Encryption</h3>
							 	</div>
						</div>
						<div class="col-md-4 banner-grid2">
							<div class="banner-subg1">
								<h3 class="mt-3"><span class="fas fa-envelope pr-3" aria-hidden="true"></span> Secure Document Sharing</h3>
							 	</div>
						</div>
				</div>
			</div>
		</section>
		<!-- //banner bottom -->
	<!-- courses -->
	 
<!-- //courses -->
	<!-- prepare -->
	<div class="prepare_wthree py-5" id="login">
		<div class="container py-md-4 mt-md-3">
			<div class="row prepare_top">
				<div class="col-lg-7 prepare_top_right">
					<h5 class="mb-3">Login</h5>
					  <form action="login" method="post">
                  <table class="tblform">
                      <tr>
                          <td>User-Id</td>
                          <td>
                              <input type="text" name="txtuserid" required class="form-control" />
                          </td>
                      </tr>
                      <tr>
                          <td>Password</td>
                          <td>
                              <input type="password" name="txtpass" required class="form-control" />
                          </td>
                      </tr>
                      <tr>
                          <td colspan="2">
                          <input type="submit" value="Submit" class="btn btn-primary"/>
                          </td>
                      </tr>
                  </table>
              </form>
				 	</div>
				<div class="col-lg-5 prepare_top_left">
				 
				</div>
				
			</div>
		</div>
	</div>
	<!-- //prepare -->
	<!-- categories -->
	<section class="categories_agile py-5" id="registration">
		<div class="container py-md-4 mt-md-3">
		<h3 class="heading-agileinfo">Registration </h3>
			<div class="row categories-top mt-md-5 pt-5">
				<div class="col-md-4 categories-left1">
				</div>
				<div class="col-md-8 categories-left">
					<div class="row">
						<div class="col-md-12 col-sm-12 categories_sub cats">
							 <br/><br/>
						   <form id="frm" action="RegUser" method="post">
									 <table class="tblform"><tr>
									 <td>
									  <table class="tblform">
									 <tr><td>UserName</td>
                <td><input type="text" class="form-control"  name="name" required></td></tr>
            <tr><td>User-ID</td>
                <td><input type="text"  class="form-control"  name="userid" required></td></tr>
                       
             <tr><td>Password</td>
                <td><input type="password"  class="form-control"  name="pass" required></td></tr>
            
			<tr>
				<td>Mobile Number</td>
				<td>
				<input type="text"  class="form-control"  name="mobile" pattern="^\d{10}$"  >
				</td>
			</tr>
			
                <tr><td>Email-ID:</td>
                    <td><input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  name="email" required></td>
                </tr><tr>
<td>Gender</td>
<td>
<input type="radio" name="gender" value="Male"   checked="true" required >Male</input>
<input type="radio" name="gender" value="Female"  required>Female</input>
</td>
</tr>
                  </table></td><td style="vertical-align: top"><table class="tblform"> 
									 <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									  <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>City
									 </td>
									 <td> 
									<div id="cities"></div>
									 </td>
									 </tr>
									  
	            <td>  Address</td>
		            <td>
		            <textarea rows="5" cols="25" name="addr" class="form-control" ></textarea>          
		            </td>
            </tr>
								 
									 
<tr>
<td>DOB</td>
<td>
<input type="date" name="dob" required class="form-control"/>
</td>
</tr>
</table></td></tr>
 

									 <tr>
									 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
									 </td></tr>
									 </table>
									 </form><br/><br/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- //categories -->
<!-- stats --> 
<!-- copyright -->
	<section class="copyright-w3layouts py-xl-4 py-3">
		<div class="container">
			<p>© 2022-2023 Data Security. All Rights Reserved 
			 
			</p>
		 
		</div>
	</section>
	<!-- //copyright -->
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Attainment</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<img src="images/bg1.jpg" class="img-fluid" alt="" />
						<p>Duis venenatis, turpis eu bibendum porttitor, sapien quam ultricies tellus, ac rhoncus risus odio eget nunc. Pellentesque ac fermentum diam. Integer eu facilisis nunc, a iaculis felis. Pellentesque pellentesque tempor enim, in dapibus turpis porttitor quis. </p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- //Modal -->
	<!-- js -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- //js-->
	<!--banner-slider-->
	<script src="js/JiSlider.js"></script>
	<script> 
		$(window).load(function () {
			$('#JiSlider').JiSlider({
				color: '#fff',
				start: 1,
				reverse: false
			}).addClass('ff')
		})
	</script>
	<!-- //banner-slider -->
	<!-- stats -->
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.countup.js"></script>
		<script>
			$('.counter').countUp();
		</script>
<!-- //stats -->

	<!-- FlexSlider-JavaScript -->
	<script defer src="js/jquery.flexslider.js"></script>
	<script type="text/javascript">
		
				$(window).load(function(){
				$('.flexslider').flexslider({
					animation: "slide",
					start: function(slider){
						$('body').removeClass('loading');
					}
			});
		});
	</script>
<!-- //FlexSlider-JavaScript -->
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	 <script src="js/bootstrap.js"></script>
</body>

</html>