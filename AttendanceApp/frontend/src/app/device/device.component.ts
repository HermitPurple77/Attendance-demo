import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { interval, Subject, takeUntil } from 'rxjs';

// Matches the IpAddress Java model
interface IpAddress {
  id: number;
  address: string;
  enable: boolean;
  lastChecked: string | null;
  status: string;
  responseTime: number | null;
}

@Component({
  selector: 'app-device',
  templateUrl: './device.component.html',
  styleUrls: ['./device.component.css']
})
export class DeviceComponent implements OnInit, OnDestroy {

  ipAddresses: IpAddress[] = [];

  // ── Backend API URL ──────────────────────────────────
  // Change this if your Spring Boot runs on a different port/context path
  private readonly API_URL = 'http://localhost:8080/api/ips';

  // Used to cleanly cancel the interval when the component is destroyed
  private destroy$ = new Subject<void>();

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.loadIpAddresses();

    // Poll the backend every 10 seconds (matches Spring Boot ping interval)
    interval(10000)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => this.loadIpAddresses());
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadIpAddresses(): void {
    this.http.get<IpAddress[]>(this.API_URL).subscribe({
      next: (data) => {
        this.ipAddresses = data;
      },
      error: (err) => {
        console.error('Error loading IP addresses:', err);
      }
    });
  }

  /** Returns a CSS colour string based on ping status */
  getStatusColor(status: string): string {
    switch (status) {
      case 'UP':    return 'green';
      case 'DOWN':  return 'red';
      case 'ERROR': return 'orange';
      default:      return 'gray';
    }
  }

  /** Returns a human-readable label for the status */
  getStatusText(status: string): string {
    switch (status) {
      case 'UP':    return 'Online';
      case 'DOWN':  return 'Offline';
      case 'ERROR': return 'Error';
      default:      return 'Unknown';
    }
  }
}
