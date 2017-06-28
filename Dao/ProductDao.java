package Dao;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
public class ProductDao {

		//a list to store all the products
		private static List<Product> data = new ArrayList<Product>();
		
		/**
		 *  initialize the products
		 */
		static{
			//run for once
			for(int i=1;i<=10;i++){
				data.add(new Product(""+i,"computer"+i,"LN00"+i,34.0+i));
			}
		}
		
		
		/**
		 * return all the product data
		 */
		public List<Product> findAll(){
			return data;
		}
		
		/**
		 * search the product by id
		 */
		public Product findById(String id){
			for(Product p:data){
				if(p.getId().equals(id)){
					return p;
				}
			}
			return null;
		}


}
