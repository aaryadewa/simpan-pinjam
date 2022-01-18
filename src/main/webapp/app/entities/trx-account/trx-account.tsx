import { ITrxAccount } from 'app/shared/model/trx-account.model';
import { IQueryParams } from 'app/shared/util/service-util';
import React from 'react';
import { Link } from 'react-router-dom';
import { Button, Col, Form, FormGroup, Input, Label, Row, Table } from 'reactstrap';
import { formatCurrency, formatDateTime, formatDateTimeISO } from '../../shared/util/formatter';
import { getEntities } from './trx-account.reducer';

interface ITrxHistoryFilter {
  dateFrom: string;
  dateTo?: string;
}

interface ITrxAccountProps {}

interface ITrxAccountState {
  loading: boolean;
  trxAccountList: ITrxAccount[];
  totalItems: number;
  filter: ITrxHistoryFilter;
}

export default class TrxAccount extends React.Component<ITrxAccountProps, ITrxAccountState> {
  constructor(props: ITrxAccountProps) {
    super(props);
    this.state = {
      loading: false,
      trxAccountList: [],
      totalItems: 0,
      filter: {
        dateFrom: '',
        dateTo: ''
      }
    };

    this.onFilterChange = this.onFilterChange.bind(this);
    this.applyFilter = this.applyFilter.bind(this);
    this.reloadList = this.reloadList.bind(this);
  }

  onFilterChange(e: any) {
    const dateFrom = e.target.value;
    const fieldName = e.target.name;
    
    this.setState({
      ...this.state,
      filter: {
        ...this.state.filter,
        [fieldName]: dateFrom,

      }
    })
  }

  componentDidMount() {
    this.loadList();
  }

  applyFilter(e) {
    e.preventDefault();
    const queryString: string[] = [];
    const { dateFrom, dateTo } = this.state.filter;
    if (!dateFrom && !dateTo) {
      return;
    }
    if (dateFrom) {
      queryString.push(`trxDate=${formatDateTimeISO(dateFrom)}`);
    }
    if (dateTo) {
      queryString.push(`trxDate=${formatDateTimeISO(dateTo)}`);
    }
    this.loadList({
      query: queryString.join('&')
    });
  }

  loadList(params?: IQueryParams) {
    this.setState({loading: true});
    getEntities(params)
      .then(res => {
        this.setState({ trxAccountList: res.data });
      })
      .catch(err => console.error('Failed to get the list of transaction accounts', err))
      .finally(() => this.setState({loading: false}));
  }

  reloadList() {
    this.setState({filter: {
      dateFrom: '',
      dateTo: ''
    }});
    this.loadList();
  }

  render() {
    return (
      <div>
        <h2 id="trx-account-heading" data-cy="TrxAccountHeading">
          Account Transactions
          <div className="d-flex justify-content-end">
            <Button className="me-2" color="primary" onClick={this.reloadList} disabled={this.state.loading}>
              Refresh List
            </Button>
          </div>
        </h2>
        <Form onSubmit={this.applyFilter}>
          <Row>
            <Col lg={3} md={6} xs={12}>
              <FormGroup>
                <Label for="dateFrom">From</Label>
                <Input
                  id="dateFrom"
                  bsSize="sm"
                  name="dateFrom"
                  placeholder="From"
                  type="date"
                  value={this.state.filter.dateFrom}
                  onChange={this.onFilterChange}
                />
              </FormGroup>
            </Col>
            <Col lg={3} md={6} xs={12}>
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
          <Row>
            <Col md={6}>
              <Button type="submit" size="sm" color="primary">Apply</Button>
            </Col>
          </Row>
        </Form>
        <div className="table-responsive" style={{ marginTop: '16px' }}>
          {this.state.trxAccountList && this.state.trxAccountList.length > 0 ? (
            <Table responsive size="sm">
              <thead>
                <tr>
                  <th>
                    Transaction Date
                  </th>
                  <th>
                    Transaction Type
                  </th>
                  <th>
                    User
                  </th>
                  <th className="text-end">
                    Amount
                  </th>
                </tr>
              </thead>
              <tbody>
                {this.state.trxAccountList.map(trxAccount => (
                  <tr key={`entity-${trxAccount.id}`} data-cy="entityTable">
                    <td>{formatDateTime(trxAccount.trxDate as string)}</td>
                    <td>{trxAccount.trxType}</td>
                    <td>{trxAccount.user ? <Link to={`../../ext-users/${trxAccount.user.id}`}>{trxAccount.user.name}</Link> : ''}</td>
                    <td className="text-end">{formatCurrency(trxAccount.amount as number)}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !this.state.loading && <div className="alert alert-warning">No Account Transactions found</div>
          )}
        </div>
      </div>
    );
  }
}
