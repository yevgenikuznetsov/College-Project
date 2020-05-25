import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Element } from './element'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ElementService {

  constructor(private http: HttpClient) { }

  public postElement(recipe: Element, email: string) {
    return this.http.post<Element>("http://localhost:8091/acs/elements/" + email, recipe);
  }

  public getElementByName(email: string, name: string): Promise<any> {
    return this.http.get<Element>("http://localhost:8091/acs/elements/" + email + "/search/byName/" + name).toPromise();
  }

  public getAllElementByEmail(email: string) {
    return this.http.get("http://localhost:8091/acs/elements/" + email);

  }
}
