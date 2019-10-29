import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from 'rxjs/index';
import {catchError, tap} from 'rxjs/internal/operators';
import {ProductDTO} from './dto/product-dto';
import {ProductCategoryDTO} from './dto/product-category-dto';
import {ProductOrderDTO} from './dto/product-order-dto';

@Injectable()
export class AppService {

  isLoggedIn = false;
  readonly apiUrl = '/api/';

  constructor(private http: HttpClient) {
  }

  login(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'auth/login', data)
      .pipe(tap(_ => this.isLoggedIn = true));
  }

  logout(): Observable<any> {
    return this.http.get<any>(this.apiUrl + 'auth/logout')
      .pipe(tap(_ => this.isLoggedIn = false));
  }

  register(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'auth/register', data)
      .pipe(
        tap(_ => this.log('register')));
  }

  user(): Observable<any> {
    return this.http.get<any>(this.apiUrl + 'auth/user')
      .pipe();
  }

  activate(token): Observable<any> {
    return this.http.get<any>(this.apiUrl + 'auth/activate', {params: new HttpParams().set('token', token)})
      .pipe();
  }

  resetPassword(data): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'auth/resetPassword', data)
      .pipe();
  }

  changePassword(data): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'auth/changePassword', data)
      .pipe();
  }

  fetchProducts(): Observable<ProductDTO[]> {
    return this.http.get<ProductDTO[]>(this.apiUrl + 'product/all')
      .pipe();
  }

  image(path: string): Observable<Blob> {
    return this.http
      .get(this.apiUrl + 'product/image', { responseType: 'blob', params: new HttpParams().set('path', path) });
  }

  fetchCategories(): Observable<ProductCategoryDTO[]> {
    return this.http.get<ProductCategoryDTO[]>(this.apiUrl + 'productCategory/all')
      .pipe();
  }

  searchProducts(data): Observable<ProductDTO[]> {
  return this.http.post<ProductDTO[]>(this.apiUrl + 'product/search', data)
      .pipe();
  }

  orderProducts(data): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'product/order', data)
      .pipe();
  }

  getOrders(): Observable<ProductOrderDTO[]> {
    return this.http.get<ProductOrderDTO[]>(this.apiUrl + 'product/orders')
      .pipe();
  }

  removeOrder(data): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'product/order/remove', data)
      .pipe();
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }

}
