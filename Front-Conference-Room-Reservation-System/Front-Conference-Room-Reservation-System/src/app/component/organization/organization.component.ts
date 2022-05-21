import { Reservation } from './../../model/Reservation';
import { ReservationService } from './../../service/reservationService/reservation.service';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ConferenceRoom } from 'src/app/model/ConferenceRoom';
import { ConferenceRoomService } from 'src/app/service/conferenceRoomService/conference-room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OrganizationService } from 'src/app/service/organizationService/organization.service';
import { Organization } from 'src/app/model/Organization';
import { LogoutService } from 'src/app/service/logutService/logout.service';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent implements OnInit {

  public conferenceRooms: ConferenceRoom[];
  public editConferenceRoom: ConferenceRoom;
  public deleteConferenceRoom: ConferenceRoom;
  public isAvailable: string;
  public organizationId: number;
  public organizationName: string;
  public emptyReservations = [];


  constructor(private conferenceRoomService: ConferenceRoomService,
    private router: Router,
    private route: ActivatedRoute,
    private organizationService: OrganizationService,
    private logoutService: LogoutService) { }

  ngOnInit() {
    let id = parseInt(this.route.snapshot.paramMap.get('organizationId'));
    this.organizationId = id;
    this.getConferenceRooms();
    this.organizationService.getOrganization(this.organizationId).subscribe(
      (response: Organization) => {
        this.organizationName = response.organizationName
      }
    )

  }

  public getConferenceRooms(): void {
    this.conferenceRoomService.getConferenceRooms(this.organizationId).subscribe(
      (response: ConferenceRoom[]) => {
        this.conferenceRooms = response;
        console.log(this.conferenceRooms);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddConferenceRoom(addForm: NgForm): void {
    document.getElementById('add-conference-room-form')!.click();
    this.conferenceRoomService.addConferenceRoom(addForm.value, this.organizationId).subscribe(
      (response: ConferenceRoom) => {
        console.log(response);
        this.getConferenceRooms();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert("Name and Identifier for conference room has to be unique!");
        addForm.reset();
      },
      (message?: HttpResponse<Response>) => {
        console.log(message.body);
      }
    );
  }

  public onUpdateConferenceRoom(conferenceRoom: ConferenceRoom): void {
    this.conferenceRoomService.udpateConferenceRoom(conferenceRoom, conferenceRoom.conferenceRoomId).subscribe(
      (response: ConferenceRoom) => {
        console.log(response);
        this.getConferenceRooms();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteConferenceRoom(conferenceRoomId: number): void {
    this.conferenceRoomService.deleteConferenceRoom(conferenceRoomId).subscribe(
      (response: void) => {
        console.log(response);
        this.getConferenceRooms();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchConferenceRooms(key: string): void {
    console.log(key);
    const results: ConferenceRoom[] = [];
    for (const conferenceRoom of this.conferenceRooms) {
      if (conferenceRoom.conferenceRoomIdentifier.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(conferenceRoom);
      }
    }
    this.conferenceRooms = results;
    if (key.length === 0) {
      this.getConferenceRooms();
    }
  }

  public onOpenModal(conferenceRoom: ConferenceRoom, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addConferenceRoomModal');
    }
    if (mode === 'edit') {
      this.editConferenceRoom = conferenceRoom;
      button.setAttribute('data-target', '#updateConferenceRoomModal');
    }
    if (mode === 'delete') {
      this.deleteConferenceRoom = conferenceRoom;
      button.setAttribute('data-target', '#deleteConferenceRoomModal');
    }
    if (mode === 'reserve') {
      this.router.navigate(['reservations', conferenceRoom.conferenceRoomId])
    }
    container!.appendChild(button);
    button.click();
  }

  public logout() {
    this.router.navigate(['login'])
  }
}
