import { IExtUser } from 'app/shared/model/ext-user.model';
import { ITrxHistory } from 'app/shared/model/trx-history.model';
import { IQueryParams } from 'app/shared/util/service-util';
import React from 'react';
import { Link } from 'react-router-dom';
import { Button, Col, Form, FormGroup, Input, Label, Row, Table } from 'reactstrap';
import { formatCurrency, formatDateTime, formatDateTimeISO } from '../../shared/util/formatter';
import { getEntities as getUsers } from '../ext-user/ext-user.reducer';
import { getEntities } from './trx-history.reducer';

interface ITrxHistoryFilter {
  userName: string;
  dateFrom: string;
  dateTo?: string;
  amountMin?: string;
  amountMax?: string;
}

interface ITrxHistoryProps {}

interface ITrxHistoryState {
  loading: boolean;
  memberList: IExtUser[];
  trxHistoryList: ITrxHistory[];
  totalItems: number;
  filter: ITrxHistoryFilter;
}

export default class TrxHistory extends React.Component<ITrxHistoryProps, ITrxHistoryState> {
  constructor(props: ITrxHistoryProps) {
    super(props);

    this.state = {
      loading: false,
      memberList: [],
      trxHistoryList: [],
      totalItems: 0,
      filter: {
        userName: '',
        dateFrom: '',
        dateTo: '',
        amountMin: '',
        amountMax: '',
      }
    };

    this.onFilterChange = this.onFilterChange.bind(this);
    this.applyFilter = this.applyFilter.bind(this);
    this.reloadList = this.reloadList.bind(this);
  }

  onFilterChange(e: any) {
    const value = e.target.value;
    const fieldName = e.target.name;
    
    this.setState({
      ...this.state,
      filter: {
        ...this.state.filter,
        [fieldName]: value,

      }
    })
  }

  componentDidMount() {
    this.loadUsers();
    this.loadList();
  }

  applyFilter(e) {
    e.preventDefault();
    const queryString: string[] = [];
    const { userName, dateFrom, dateTo, amountMin, amountMax } = this.state.filter;
    if (!userName && !dateFrom) {
      return;
    }
    if (userName) {
      queryString.push(`userName=${userName}`);
    }
    if (dateFrom) {
      queryString.push(`trxDate=${formatDateTimeISO(dateFrom)}`);
    }
    if (dateTo) {
      queryString.push(`trxDate=${formatDateTimeISO(dateTo)}`);
    }
    if (amountMin) {
      queryString.push(`amount=${amountMin}`);
    }
    if (amountMax) {
      queryString.push(`amount=${amountMax}`);
    }
    this.loadList({
      query: queryString.join('&')
    });
  }

  formatCurrency(value: number) {
    return formatCurrency(value);
  }

  formatDateTime(value: string) {
    return formatDateTime(value);
  }

  loadUsers() {
    getUsers()
      .then(res => {
        this.setState({ memberList: res.data });
      })
  }

  loadList(params?: IQueryParams) {
    this.setState({loading: true});
    getEntities(params)
      .then(res => {
        this.setState({ trxHistoryList: res.data });
      })
      .catch(err => console.error('Failed to get the list of transaction history', err))
      .finally(() => this.setState({loading: false}));
  }

  reloadList() {
    this.setState({filter: {
      userName: '',
      dateFrom: '',
      dateTo: '',
      amountMin: '',
      amountMax: ''
    }});
    this.loadList();
  }

  render() {
    return (
      <div>
        <h2 id="trx-account-heading" data-cy="TrxAccountHeading">
          Transaction History
          <div className="d-flex justify-content-end">
            <Button className="me-2" color="primary" onClick={this.reloadList} disabled={this.state.loading}>
              Refresh List
            </Button>
          </div>
        </h2>
        <Form onSubmit={this.applyFilter}>
          <Row lg={3} sm={6} xs={12}>
            <Col>
              <FormGroup>
                <Label for="userName">Member Name</Label>
                <Input
                  id="userName"
                  bsSize="sm"
                  name="userName"
                  type="select"
                  value={this.state.filter.userName}
                  onChange={this.onFilterChange}
                >
                  <option value=""></option>
                  {this.state.memberList.map(member => (
                    <option
                      key={member.id}
                      value={member.name}
                    >{member.name}</option>
                  ))}
                </Input>
              </FormGroup>
            </Col>
          </Row>
          <Row lg={3} sm={6} xs={12}>
            <Col>
              <FormGroup>
                <Label for="dateFrom">From</Label>
                <Input
                  id="dateFrom"
                  bsSize="sm"
                  name="dateFrom"
                  placeholder="From"
                  type="date"
                  value={this.state.filter.dateTo}
                  onChange={this.onFilterChange}
                />
              </FormGroup>
            </Col>
            <Col>
              <FormGroup>
                <Label for="dateTo">To</Label>
                <Input
                  id="dateTo"
                  bsSize="sm"
                  name="dateTo"
                  placeholder="To"
                  type="date"
                  value={this.state.filter.dateTo}
                  onChange={this.onFilterChange}
                />
              </FormGroup>
            </Col>
          </Row>
          <Row lg={3} sm={6} xs={12}>
            <Col>
              <FormGroup>
                <Label for="amountMin">Min. Amount</Label>
                <Input
                  id="amountMin"
                  bsSize="sm"
                  name="amountMin"
                  placeholder="Min. Amount"
                  type="number"
                  value={this.state.filter.amountMin}
                  onChange={this.onFilterChange}
                />
              </FormGroup>
            </Col>
            <Col>
              <FormGroup>
                <Label for="amountMax">Max. Amount</Label>
                <Input
                  id="amountMax"
                  bsSize="sm"
                  name="amountMax"
                  placeholder="Max. Amount"
                  type="number"
                  value={this.state.filter.amountMax}
                  onChange={this.onFilterChange}
                />
              </FormGroup>
            </Col>
          </Row>
          <Row>
            <Col>
              <Button type="submit" size="sm" color="primary">Apply</Button>
            </Col>
          </Row>
        </Form>
        <div className="table-responsive" style={{ marginTop: '16px' }}>
          {this.state.trxHistoryList && this.state.trxHistoryList.length ? (
            <Table responsive size="sm">
              <thead>
                <tr>
                  <th>
                    Account No
                  </th>
                  <th>
                    Transaction Date
                  </th>
                  <th>
                    Transaction Type
                  </th>
                  <th>
                    User Name
                  </th>
                  <th>
                    First Name
                  </th>
                  <th>
                    Last Name
                  </th>
                  <th className="text-end">
                    Amount
                  </th>
                </tr>
              </thead>
              <tbody>
                {this.state.trxHistoryList.map(trxHistory => (
                  <tr key={`entity-${trxHistory.id}`} data-cy="entityTable">
                    <td>{trxHistory.accountNo}</td>
                    <td>{this.formatDateTime(trxHistory.trxDate as string)}</td>
                    <td>{trxHistory.trxType}</td>
                    <td>{trxHistory.userName}</td>
                    <td>{trxHistory.firstName}</td>
                    <td>{trxHistory.lastName}</td>
                    <td className="text-end">{this.formatCurrency(trxHistory.amount as number)}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !this.state.loading && <div className="alert alert-warning">No Transaction History found</div>
          )}
        </div>
      </div>
    );
  }
}
