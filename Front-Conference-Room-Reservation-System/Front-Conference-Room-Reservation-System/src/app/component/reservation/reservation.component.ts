import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Reservation } from 'src/app/model/Reservation';
import { ReservationService } from 'src/app/service/reservationService/reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  public reservations: Reservation[];
  public editReservation: Reservation;
  public deleteReservation: Reservation;
  public conferenceRoomId: number;
  public conferenceRoomName: string;


  constructor(private reservationService: ReservationService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    let id = parseInt(this.route.snapshot.paramMap.get('conferenceRoomId'));
    this.conferenceRoomId = id;
    this.getReservations();
  }

  public getReservations(): void {
    this.reservationService.getReservations(this.conferenceRoomId).subscribe(
      (response: Reservation[]) => {
        this.reservations = response;
        console.log(this.reservations);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddReservation(addForm: NgForm): void {
    document.getElementById('add-reservation-room-form')!.click();
    this.reservationService.addReservation(addForm.value, this.conferenceRoomId).subscribe(
      (response: Reservation) => {
        console.log(response);
        this.getReservations();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert.arguments("Reservation time collides with already existing reservation");
        addForm.reset();
      }
    );
  }

  public onUpdateReservation(reservation: Reservation): void {
    this.reservationService.udpateReservation(reservation).subscribe(
      (response: Reservation) => {
        console.log(response);
        this.getReservations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteReservation(reservationId: number): void {
    this.reservationService.deleteReservation(reservationId).subscribe(
      (response: void) => {
        console.log(response);
        this.getReservations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchReservations(key: string): void {
    console.log(key);
    const results: Reservation[] = [];
    for (const reservation of this.reservations) {
      if (reservation.reservationIdentifier.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(reservation);
      }
    }
    this.reservations = results;
    if (key.length === 0) {
      this.getReservations();
    }
  }

  public onOpenModal(reservation: Reservation, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addReservationModal');
    }
    if (mode === 'edit') {
      this.editReservation = reservation;
      button.setAttribute('data-target', '#updateReservationModal');
    }
    if (mode === 'delete') {
      this.deleteReservation = reservation;
      button.setAttribute('data-target', '#deleteReservationModal');
    }
    container!.appendChild(button);
    button.click();
  }

}
