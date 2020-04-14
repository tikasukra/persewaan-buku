<?php
//requried headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

//include files
include_once '../config/database.php';
include_once '../objects/product.php';

//create database and product object
$database = new Database();
$db = $database->getConnection();

//initialize object
$product = new Product($db);

//retrieving query
$stmt=$product->read();
$num = $stmt->rowCount();

if($num>0){
//Array untuk produk
	$products_arr=array();
	$products_arr["records"]=array();

	//membaca isi tabel baris per baris
	while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
		//extract(row) merubah $row['nama'] menjadi $nama
		extract($row);
		$product_item=array(
			"id"=>$id,
			"name"=>$name,
			"description"=>html_entity_decode($description),
			"price"=>$price,
			"category_id"=>$category_id
		);

		array_push($products_arr["records"], $product_item);
	}

	//set response code - 200 ok
	http_response_code(200);

	//encode ke format json
	echo json_encode($products_arr);


}else{
	//set response code -404 not found
	http_response_code(404);

	//item not found exception
	echo json_encode(
		array("message"=> "Prdouk tidak ditemukan.")
	);
}
?>