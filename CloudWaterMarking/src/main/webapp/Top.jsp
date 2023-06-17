 <%@page import="java.util.Date"%>
<%@page import="services.UpdateCloudRent"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
	<title>   Enhancing Data Security Using Digital Watermarking</title>
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
	<link href="css/font-awesome.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="//fonts.googleapis.com/css?family=Oswald:400,500,600,700" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">

</head>

<body><%
try
{  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
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
                                <a class="nav-link" href="<%=session.getAttribute("utype").toString().trim()%>">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                           <%if(session.getAttribute("utype").toString().trim().equals("admin"))
							{%>
						  <li class="nav-item active  mr-3"><a  class="nav-link"  href="viewUsers">   View Users </a></li>
							  <li class="nav-item dropdown mr-3">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    Reports
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                
                                <a class="dropdown-item" href="MonthlyCloudUsage.jsp?page=admin">Total Cloud Usage</a>
		 							 <a class="dropdown-item" href="MonthlyCloudRent.jsp">Monthly Cloud Usage Statistics</a>
									 <a class="dropdown-item" href="YearlyPayment History.jsp">Payment Summary</a>
							
									  <a class="dropdown-item" href="MonthlyPayment.jsp">Cloud Payments</a>
                                </div>
                            </li>
							 
							
							<%} else if(session.getAttribute("utype").toString().trim().equals("user"))
							{%>
							  <li class="nav-item active  mr-3"><a  class="nav-link"  href="uploadDocs">Upload Documents</a></li>
         <li class="nav-item active  mr-3"><a  class="nav-link"  href="viewMyDocs">My Documents</a></li>
 
       		<li class="nav-item active  mr-3"><a  class="nav-link"  href="newGroup">Groups</a> </li>
       		 				  
                <li class="nav-item dropdown mr-3">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    Reports
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                
                                <a class="dropdown-item" href="MonthlyCloudUsage.jsp?page=user">Total Cloud Usage</a>
									 <a class="dropdown-item" href="MonthlyCloudRent.jsp">Monthly Cloud Usage Statistics</a>
									 <a class="dropdown-item" href="YearlyUserPayments.jsp">Payment Summary</a>
									
                                </div>
                            </li>
							 
						 	<%} %>
						
					<li class="nav-item active  mr-3"><a  class="nav-link"   href="logout" >   Logout </a></li>
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
	<section class="banner-1">
	</section>
	<!-- //banner -->
 <!-- contact -->


<body>
 
<div class="container-fluid"><div class="banner1"></div></div>
  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section class="breadcrumbs">
      <div class="container">
 
          <p class="para">
            Logged in as <%=session.getAttribute("username").toString() %>( <%=session.getAttribute("utype").toString() %>)
            </p>
        
    <!--// end-smoth-scrolling -->
    <%
//    Date dt=new Date();
//UpdateCloudRent upd=new UpdateCloudRent();
//     upd.updcloudrent((dt.getMonth()+1),(dt.getYear()+1900));
}catch(Exception ex)
{
    	System.out.println("err="+ex.getMessage());
    	 
}%>
         
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
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  