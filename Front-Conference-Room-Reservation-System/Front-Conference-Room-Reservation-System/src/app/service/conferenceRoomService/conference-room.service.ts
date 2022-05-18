import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConferenceRoom } from 'src/app/model/ConferenceRoom';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConferenceRoomService {

  private apiServerUrl = environment.apiServerUrl + "/conference-room";
  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:admin')
    })
  };

  constructor(private http: HttpClient) {
  }

  public getConferenceRooms(organizationId: number): Observable<ConferenceRoom[]> {
    return this.http.get<ConferenceRoom[]>(`${this.apiServerUrl}/${organizationId}/all`, this.httpOptions)
  }

  public getConferenceRoom(conferenceRoomId: number): Observable<ConferenceRoom> {
    return this.http.get<ConferenceRoom>(`${this.apiServerUrl}/${conferenceRoomId}`, this.httpOptions)
  }

  public addConferenceRoom(conferenceRoom: ConferenceRoom): Observable<ConferenceRoom> {
    return this.http.post<ConferenceRoom>(`${this.apiServerUrl}`, conferenceRoom, this.httpOptions)
  }

  public udpateConferenceRoom(conferenceRoom: ConferenceRoom, conferenceRoomId: number): Observable<ConferenceRoom> {
    return this.http.put<ConferenceRoom>(`${this.apiServerUrl}/${conferenceRoomId}`, conferenceRoom, this.httpOptions)
  }

  public deleteConferenceRoom(conferenceRoomId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${conferenceRoomId}`, this.httpOptions)
  }
}
