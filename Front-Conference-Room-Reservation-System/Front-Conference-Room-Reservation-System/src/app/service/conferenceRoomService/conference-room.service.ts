import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConferenceRoom } from 'src/app/model/ConferenceRoom';

@Injectable({
  providedIn: 'root'
})
export class ConferenceRoomService {

  private apiServerUrl = '';

  constructor(private http: HttpClient) {
  }

  public getConferenceRooms(): Observable<ConferenceRoom[]> {
    return this.http.get<ConferenceRoom[]>(`${this.apiServerUrl}/conferenceroom/all`)
  }

  public addConferenceRoom(conferenceRoom: ConferenceRoom): Observable<ConferenceRoom> {
    return this.http.post<ConferenceRoom>(`${this.apiServerUrl}/conferenceroom/add`, conferenceRoom)
  }

  public udpateConferenceRoom(conferenceRoom: ConferenceRoom): Observable<ConferenceRoom> {
    return this.http.put<ConferenceRoom>(`${this.apiServerUrl}/conferenceroom/udpate`, conferenceRoom)
  }

  public deleteConferenceRoom(conferenceRoomIdentifier: string): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/conferenceroom/delete/${conferenceRoomIdentifier}`)
  }
}
