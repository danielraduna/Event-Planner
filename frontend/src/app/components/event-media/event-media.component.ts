import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {ImageEventService} from "../../services/image-event.service";
import {ImageEvent} from "../../entities/image-event";
import {Event} from "../../entities/event";

@Component({
  selector: 'app-event-media',
  templateUrl: './event-media.component.html',
  styleUrls: ['./event-media.component.scss']
})
export class EventMediaComponent  implements OnInit{
  eventId!: number;
  event!: Event;
  images: ImageEvent[] = [];
  responsiveOptions!: any[];
  activeIndex: number = 0;
  displayCustom: boolean = false;
  constructor(private route: ActivatedRoute,
              private eventService: EventService,
              private imageEventService: ImageEventService
  ) { }

  ngOnInit(): void {
    this.eventId = +this.route.snapshot.paramMap.get('eventId')!;
    this.eventService.getEventById(this.eventId).subscribe(data => {
      this.event = data.body!;
      console.log(this.event)

      this.imageEventService.getEventImagesByEvent(this.event.id!).subscribe(data2 => {
        this.images = data2;
      })
    });
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 5
      },
      {
        breakpoint: '768px',
        numVisible: 3
      },
      {
        breakpoint: '560px',
        numVisible: 1
      }
    ];
  }

  onFileSelected(event: any) {
    const files: FileList = event.files;
    if (files && files.length > 0) {
      for (let i = 0; i < files.length; i++) {
        const file: File = files[i];
        const reader = new FileReader();
        reader.onload = (e: ProgressEvent<FileReader>) => {
          const imageData = e.target!.result as string;
          const image: ImageEvent = {
            imageData: imageData,
            event: this.event
          };
          this.images.push(image);
          this.imageEventService.createEventImage(image).subscribe(data => {
            this.imageEventService.assignImageToEvent(data.id!, this.event.id!).subscribe();
          });
        };
        reader.readAsDataURL(file);
      }
    }
  }


  imageClick(index: number) {
    this.activeIndex = index;
    this.displayCustom = true;
  }
}
