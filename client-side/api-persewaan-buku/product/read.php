<?php

	// required headers untuk memberi tahu server http bahwa kita berkomunikasi lewat json
	header("Access-Control-Allow-Origin: *");
	header("Content-Type: application/json; charset=UTF-8");

	// include files
	include_once '../config/database.php';
	include_once '../objects/product.php';

	// create database and product object
	$database = new Database();
	$db = $database->getConnection();

	// initialize object
	$product = new Product($db);

	// retrieving query
	$stmt = $product->read();
	$num = $stmt->rowCount();

	if ($num > 0) {
		# code...
		// array untuk produk
		$products_arr=array();
		$products_arr["records"]=array();

		// membaca isi tabel baris per baris
		while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
			// extract($row) MERUBAH $row['nama'] menjadi $nama
			extract($row);
			$product_item = array(
				"kode_buku" => $kode_buku,
				"judul_buku" => $judul_buku,
				"pengarang" => $pengarang,
				"penerbit" => $penerbit,
				"kategori_buku" => $kategori_buku,
				"status_buku" => $status_buku
			);

			array_push($products_arr["records"], $product_item);
		}

			// set response code - 200 OK
			http_response_code(200);

			// emcode ke format json
			echo json_encode($products_arr);
			
	} else {


		// set response code - 404 Not Found
		http_response_code(404);

		// item not found exception
			echo json_encode(
				array("message" => "Produk tidak ditemukan.")
			);

	}

?>