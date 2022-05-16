import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiServerUrl = '';

  constructor(private http: HttpClient) {
  }

  public getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiServerUrl}/reservation/all`)
  }

  public addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`${this.apiServerUrl}/reservation/add`, reservation)
  }

  public udpateReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiServerUrl}/reservation/udpate`, reservation)
  }

  public deleteReservation(reservationIdentifier: string): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/reservation/delete/${reservationIdentifier}`)
  }
}
