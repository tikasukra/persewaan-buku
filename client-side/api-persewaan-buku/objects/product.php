<?php

	class Product{
		// koneksi database dan nama tabel
		private $conn;
		private $table_name = "buku";

		// object attribute
		public $kode_buku;
		public $judul_buku;
		public $buku_favorit;
		public $pengarang;
		public $penerbit;
		public $kategori_buku;
		public $status_buku;

		// contructor : db connection
		public function __construct($db) {
			$this->conn = $db;
		}

		function read(){
			// query
			$query = "SELECT
					  	p.kode_buku,
					  	p.judul_buku,
					  	p.pengarang,
					  	p.penerbit,
					  	p.kategori_buku,
                        p.status_buku
					  FROM
					  	".$this->table_name . " p
					  	ORDER BY p.kategori_buku DESC";

			// prepare query statement
			$stmt = $this->conn->prepare($query);

			// eksekusi query
			$stmt->execute();
			return $stmt;
		}


// create product
	function create(){
  
    // query to insert record
    $query = "INSERT INTO
                " . $this->table_name . "
            SET
                judul_buku=:judul_buku, pengarang=:pengarang, penerbit=:penerbit, kategori_buku=:kategori_buku, status_buku=:status_buku";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->judul_buku=htmlspecialchars(strip_tags($this->judul_buku));
    $this->pengarang=htmlspecialchars(strip_tags($this->pengarang));
    $this->penerbit=htmlspecialchars(strip_tags($this->penerbit));
    $this->kategori_buku=htmlspecialchars(strip_tags($this->kategori_buku));
    $this->status_buku=htmlspecialchars(strip_tags($this->status_buku));
  
 
    // bind values
    $stmt->bindParam(":judul_buku", $this->judul_buku);
    $stmt->bindParam(":pengarang", $this->pengarang);
    $stmt->bindParam(":penerbit", $this->penerbit);
    $stmt->bindParam(":kategori_buku", $this->kategori_buku);
    $stmt->bindParam(":status_buku", $this->status_buku);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
      
}

// update the product
function update(){
  
    // update query
    $query = "UPDATE
                " . $this->table_name . "
            SET
                judul_buku = :judul_buku,
                pengarang = :pengarang,
                penerbit = :penerbit,
                kategori_buku = :kategori_buku,
                status_buku = : status_buku
            WHERE
                kode_buku = :kode_buku";
  
    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->judul_buku=htmlspecialchars(strip_tags($this->judul_buku));
    $this->pengarang=htmlspecialchars(strip_tags($this->pengarang));
    $this->penerbit=htmlspecialchars(strip_tags($this->penerbit));
    $this->kategori_buku=htmlspecialchars(strip_tags($this->kategori_buku));
    $this->status_buku=htmlspecialchars(strip_tags($this->status_buku));
  
    // bind new values
    $stmt->bindParam(":judul_buku", $this->judul_buku);
    $stmt->bindParam(":pengarang", $this->pengarang);
    $stmt->bindParam(":penerbit", $this->penerbit);
    $stmt->bindParam(":kategori_buku", $this->kategori_buku);
    $stmt->bindParam(":status_buku", $this->status_buku);
  
    // execute the query
    if($stmt->execute()){
        return true;
    }
  
    return false;
}

// delete the product
function delete(){
  
    // delete query
    $query = "DELETE FROM " . $this->table_name . " WHERE kode_buku = ?";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->kode_buku=htmlspecialchars(strip_tags($this->kode_buku));
  
    // bind id of record to delete
    $stmt->bindParam(1, $this->kode_buku);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
}

}
?>