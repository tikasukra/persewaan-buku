<?php
	class Database {
		// specify your own database credential
		private $host = "localhost";
		private $db_name = "persewaan_buku";
		private $username = "root";
		private $password = "";
		public $conn;

		// get the database connection 

		public function getConnection(){
			$this->conn = null;

			try{
				$this->conn = new PDO("mysql:host=".$this->host.";dbname=".$this->db_name,
				$this->username, $this->password);
				$this->conn->exec("set names utf8");
			} catch(PDOException $exeption) {
				echo "Connection error: ". $exeption->getMessage();
			}
			return $this->conn;
		}
	}
?>