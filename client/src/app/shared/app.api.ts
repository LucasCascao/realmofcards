import {HttpHeaders} from '@angular/common/http';

export const API_URL = 'http://localhost:8080';
// export const API_URL = 'http://192.168.15.20:8080';

export const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
