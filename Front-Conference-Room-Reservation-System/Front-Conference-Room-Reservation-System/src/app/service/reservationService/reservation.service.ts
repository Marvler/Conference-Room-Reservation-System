import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/model/Reservation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiServerUrl = environment.apiServerUrl + "/reservation";
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  };

  constructor(private http: HttpClient) {
  }

  public getReservations(conferenceRoomId: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiServerUrl}/${conferenceRoomId}/all`, this.httpOptions)
  }

  public getReservation(reservationId: number): Observable<Reservation> {
    return this.http.get<Reservation>(`${this.apiServerUrl}/${reservationId}`, this.httpOptions)
  }

  public addReservation(reservation: Reservation, conferenceRoomId: number): Observable<Reservation> {
    reservation.conferenceRoomId = conferenceRoomId;
    return this.http.post<Reservation>(`${this.apiServerUrl}`, reservation, this.httpOptions)
  }

  public udpateReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiServerUrl}/${reservation.reservationId}`, reservation, this.httpOptions)
  }

  public deleteReservation(reservationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${reservationId}`, this.httpOptions)
  }
}
